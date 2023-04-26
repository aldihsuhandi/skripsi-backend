package id.thesis.shumishumi.core.processor.user.forgotpassword;

import id.thesis.shumishumi.common.service.ContentService;
import id.thesis.shumishumi.common.service.EmailService;
import id.thesis.shumishumi.common.service.ResetPasswordService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.user.forgotpassword.ForgotPasswordSendRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class ForgotPasswordProcessor implements BaseProcessor {

    @Autowired
    private EmailService emailService;

    @Autowired
    private ResetPasswordService resetPasswordService;

    @Autowired
    private UserService userService;

    @Autowired
    private ContentService contentService;

    @Value("${frontend.domain.url}")
    private String urlDomain;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        ForgotPasswordSendRequest request = (ForgotPasswordSendRequest) baseRequest;
        String email = request.getEmail();

        UserVO userVO = userService.queryByEmail(email, true);
        AssertUtil.isNotNull(userVO, "user doesn't exist", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(userVO.isActive(), "user is not active", ShumishumiErrorCodeEnum.USER_NOT_ACTIVE);
        AssertUtil.isExpected(!userVO.isDeleted(), "user doesn't exist", ShumishumiErrorCodeEnum.USER_NOT_FOUND);

        String uuid = resetPasswordService.resetPassword(email);

        String urlTemplate = contentService.queryContent(CommonConst.FORGOT_PASSWORD_URL_FORMAT);
        String url = String.format(urlTemplate, urlDomain, uuid);

        emailService.sendForgotPasswordEmail(email, url);
    }
}
