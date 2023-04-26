package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.user.forgotpassword.ForgotPasswordQueryRequest;
import id.thesis.shumishumi.facade.request.user.forgotpassword.ForgotPasswordSendRequest;
import id.thesis.shumishumi.facade.result.user.forgotpassword.ForgotPasswordQueryResult;
import id.thesis.shumishumi.facade.result.user.forgotpassword.ForgotPasswordSendResult;

public interface ForgotPasswordFacade {
    ForgotPasswordSendResult send(ForgotPasswordSendRequest request);

    ForgotPasswordQueryResult query(ForgotPasswordQueryRequest request);
}
