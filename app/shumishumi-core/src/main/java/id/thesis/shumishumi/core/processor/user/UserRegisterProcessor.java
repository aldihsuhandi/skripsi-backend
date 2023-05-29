package id.thesis.shumishumi.core.processor.user;

import id.thesis.shumishumi.common.model.request.user.UserCreateInnerRequest;
import id.thesis.shumishumi.common.service.ImageService;
import id.thesis.shumishumi.common.service.OTPService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.converter.UserRequestConverter;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.enumeration.OTPTypeEnum;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.ImageVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.user.UserRegisterRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class UserRegisterProcessor implements BaseProcessor {

    @Autowired
    private UserService userService;

    @Autowired
    private OTPService otpService;

    @Autowired
    private ImageService imageService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        UserRegisterRequest registerRequest = (UserRegisterRequest) baseRequest;

        checkExistingUser(registerRequest);

        String userId = FunctionUtil.generateUUID();
        registerRequest.setPassword(FunctionUtil.hashPassword(registerRequest.getPassword()));

        UserCreateInnerRequest innerRequest = UserRequestConverter.toInnerRequest(registerRequest, userId);
        String profilePicture = insertProfilePicture(registerRequest.getProfilePicture(), userId);
        innerRequest.setProfilePicture(profilePicture);

        userService.register(innerRequest);


        userService.queryById(userId, false);
        otpService.send(registerRequest.getEmail(), OTPTypeEnum.USER_ACTIVATION.getName());
    }

    private void checkExistingUser(UserRegisterRequest registerRequest) throws ShumishumiException {
        UserVO userVO = userService.queryByEmail(registerRequest.getEmail(), true);
        AssertUtil.isExpected(userVO == null || userVO.isDeleted(), "email already used by another user", ShumishumiErrorCodeEnum.USER_ALREADY_EXIST);

        userVO = userService.queryByPhoneNumber(registerRequest.getPhoneNumber(), true);
        AssertUtil.isExpected(userVO == null || userVO.isDeleted(), "phone number already used by another user", ShumishumiErrorCodeEnum.USER_ALREADY_EXIST);

        userVO = userService.queryByUsername(registerRequest.getUsername(), true);
        AssertUtil.isExpected(userVO == null || userVO.isDeleted(), "username already used by another user", ShumishumiErrorCodeEnum.USER_ALREADY_EXIST);
    }

    private String insertProfilePicture(MultipartFile profilePicture, String userId) throws ShumishumiException {
        if (profilePicture == null) {
            return "";
        }

        ImageVO imageVO = new ImageVO(profilePicture);
        imageService.upload(imageVO);

        return imageVO.getImageId();
    }
}
