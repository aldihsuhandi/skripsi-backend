package id.thesis.shumishumi.core.processor.user.forgotpassword;

import id.thesis.shumishumi.common.service.ResetPasswordService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.user.forgotpassword.ForgotPasswordQueryRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.user.forgotpassword.ForgotPasswordQueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class ForgotPasswordQueryProcessor implements BaseProcessor {

    @Autowired
    private ResetPasswordService resetPasswordService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        ForgotPasswordQueryRequest request = (ForgotPasswordQueryRequest) baseRequest;
        ForgotPasswordQueryResult result = (ForgotPasswordQueryResult) baseResult;

        String email = resetPasswordService.queryResetPassword(request.getUuid());
        AssertUtil.isExpected(StringUtils.isNotEmpty(email), "forgot password request is not found", ShumishumiErrorCodeEnum.REQUEST_NOT_FOUND);

        resetPasswordService.invalidateResetPassword(request.getUuid());

        result.setEmail(email);
    }
}
