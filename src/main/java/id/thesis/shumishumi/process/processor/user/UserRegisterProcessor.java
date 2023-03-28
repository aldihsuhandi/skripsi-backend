package id.thesis.shumishumi.process.processor.user;

import id.thesis.shumishumi.common.converter.UserRequestConverter;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.request.user.UserCreateInnerRequest;
import id.thesis.shumishumi.common.model.request.user.UserUpdateInnerRequest;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.service.OTPService;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.process.processor.BaseProcessor;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.user.UserRegisterRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Blob;

public class UserRegisterProcessor implements BaseProcessor {

    @Autowired
    private UserService userService;

    @Autowired
    private OTPService otpService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        UserRegisterRequest registerRequest = (UserRegisterRequest) baseRequest;

        checkExistingUser(registerRequest);

        String userId = FunctionUtil.generateUUID();
        registerRequest.setPassword(FunctionUtil.hashPassword(registerRequest.getPassword()));

        UserCreateInnerRequest innerRequest = UserRequestConverter.toInnerRequest(registerRequest, userId);
        userService.register(innerRequest);

        insertProfilePicture(registerRequest.getProfilePicture(), userId);

        userService.queryById(userId, false);
    }

    private void checkExistingUser(UserRegisterRequest registerRequest) throws ShumishumiException {
        UserVO userVO = userService.queryByEmail(registerRequest.getEmail(), true);
        AssertUtil.isExpected(userVO == null || !userVO.isActive(), "email already used by another user", ShumishumiErrorCodeEnum.USER_ALREADY_EXIST);

        userVO = userService.queryByPhoneNumber(registerRequest.getPhoneNumber(), true);
        AssertUtil.isExpected(userVO == null || !userVO.isActive(), "phone number already used by another user", ShumishumiErrorCodeEnum.USER_ALREADY_EXIST);
    }

    private void insertProfilePicture(Blob profilePicture, String userId) throws ShumishumiException {
        if (profilePicture == null) {
            return;
        }

        UserUpdateInnerRequest innerRequest = UserRequestConverter.toInnerRequest(userId, profilePicture);
        userService.updateProfilePicture(innerRequest);
    }
}
