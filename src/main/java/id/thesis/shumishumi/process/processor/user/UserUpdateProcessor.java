/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.process.processor.user;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.context.UserUpdateContext;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.common.util.converter.UserRequestConverter;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.process.processor.BaseProcessor;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.user.UserUpdateRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: UserUpdateProcessor.java, v 0.1 2022‐12‐27 1:51 PM Aldih Suhandi Exp $$
 */
public class UserUpdateProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult result, BaseRequest request) throws ShumishumiException {
        UserUpdateRequest updateRequest = (UserUpdateRequest) request;
        UserVO userVO = queryUserFromSession(updateRequest.getSessionId());
        UserUpdateContext updateContext = updateRequest.getUserUpdateContext();

        validateEmail(updateContext.getEmail(), userVO.getEmail());
        validatePhoneNumber(updateContext.getPhoneNumber(), userVO.getPhoneNumber());

        fillEmptyUpdateContext(updateContext, userVO);
        userService.update(UserRequestConverter.toInnerRequest(userVO.getUserId(), updateContext));
        userService.refreshCache(new ArrayList<>(Collections.singletonList(userVO.getUserId())), false);
    }

    private void fillEmptyUpdateContext(UserUpdateContext updateContext, UserVO userVO) {
        updateContext.setEmail(checkIfNotEmpty(updateContext.getEmail()) ? updateContext.getEmail() : userVO.getEmail());
        updateContext.setPassword(checkIfNotEmpty(updateContext.getPassword()) ?
                FunctionUtil.hashPassword(updateContext.getPassword()) : userVO.getPassword());
        updateContext.setUsername(checkIfNotEmpty(updateContext.getUsername()) ?
                updateContext.getUsername() : userVO.getUsername());
        updateContext.setPhoneNumber(checkIfNotEmpty(updateContext.getPhoneNumber()) ?
                updateContext.getPhoneNumber() : userVO.getPhoneNumber());
        updateContext.setProfilePicture(updateContext.getProfilePicture() != null ?
                updateContext.getProfilePicture() : userVO.getProfilePicture());
    }

    private void validateEmail(String email, String currentEmail) throws ShumishumiException {
        if (email == null || email.isEmpty() || email.equals(currentEmail)) {
            return;
        }

        UserVO userVO = userService.queryByEmail(email, true);
        AssertUtil.isNull(userVO, "email is already taken", ShumishumiErrorCodeEnum.USER_ALREADY_EXIST);
    }

    private void validatePhoneNumber(String phoneNumber, String currentPhoneNumber) throws ShumishumiException {
        if (phoneNumber == null || phoneNumber.isEmpty() || phoneNumber.equals(currentPhoneNumber)) {
            return;
        }

        UserVO userVO = userService.queryByPhoneNumber(phoneNumber, true);
        AssertUtil.isNull(userVO, "phoneNumber already belong to another user", ShumishumiErrorCodeEnum.USER_ALREADY_EXIST);
    }

    private boolean checkIfNotEmpty(String s) {
        return s != null && !s.isEmpty();
    }

    private UserVO queryUserFromSession(String sessionId) {
        SessionVO sessionVO = sessionService.query(sessionId);
        return userService.queryById(sessionVO.getUserId(), true);
    }
}
