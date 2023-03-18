/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.process.processor.user;

import id.thesis.shumishumi.common.converter.UserRequestConverter;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.context.UserUpdateContext;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.FunctionUtil;
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
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserUpdateProcessor.java, v 0.1 2022‐12‐27 1:51 PM Aldih Suhandi Exp $$
 */
public class UserUpdateProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        UserUpdateRequest updateRequest = (UserUpdateRequest) baseRequest;
        UserVO userVO = queryUserFromSession(updateRequest.getSessionId());
        UserUpdateContext updateContext = updateRequest.getUserUpdateContext();

        AssertUtil.isExpected(FunctionUtil.verifyHash(updateRequest.getPassword(), userVO.getPassword())
                , "password", ShumishumiErrorCodeEnum.AUTHENTICATION_FAILED);

        validateEmail(updateContext.getEmail(), userVO.getEmail());
        validatePhoneNumber(updateContext.getPhoneNumber(), userVO.getPhoneNumber());

        FunctionUtil.fillEmptyUpdateContext(updateContext, userVO);
        userService.update(UserRequestConverter.toInnerRequest(userVO.getUserId(), updateContext));
        userService.refreshCache(new ArrayList<>(Collections.singletonList(userVO.getUserId())), false);
    }

    private void validateEmail(String email, String currentEmail) throws ShumishumiException {
        if (email == null || email.isEmpty() || email.equals(currentEmail)) {
            return;
        }

        UserVO userVO = userService.queryByEmail(email, true);
        AssertUtil.isExpected(userVO == null || !userVO.isActive(), "email already used by another user", ShumishumiErrorCodeEnum.USER_ALREADY_EXIST);
    }

    private void validatePhoneNumber(String phoneNumber, String currentPhoneNumber) throws ShumishumiException {
        if (phoneNumber == null || phoneNumber.isEmpty() || phoneNumber.equals(currentPhoneNumber)) {
            return;
        }

        UserVO userVO = userService.queryByPhoneNumber(phoneNumber, true);
        AssertUtil.isExpected(userVO == null || !userVO.isActive(), "phone number already used by another user", ShumishumiErrorCodeEnum.USER_ALREADY_EXIST);
    }

    private UserVO queryUserFromSession(String sessionId) {
        SessionVO sessionVO = sessionService.query(sessionId);
        return userService.queryById(sessionVO.getUserId(), true);
    }
}
