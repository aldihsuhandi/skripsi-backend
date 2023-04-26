package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.facade.api.ForgotPasswordFacade;
import id.thesis.shumishumi.facade.request.user.forgotpassword.ForgotPasswordQueryRequest;
import id.thesis.shumishumi.facade.request.user.forgotpassword.ForgotPasswordSendRequest;
import id.thesis.shumishumi.facade.result.user.forgotpassword.ForgotPasswordQueryResult;
import id.thesis.shumishumi.facade.result.user.forgotpassword.ForgotPasswordSendResult;
import id.thesis.shumishumi.foundation.model.result.ResetPasswordDO;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

public class ForgotPasswordFacadeTest extends FacadeTestBase {

    @Autowired
    private ForgotPasswordFacade forgotPasswordFacade;

    @Test
    public void forgotPasswordSendTest_SUCCESS() {
        ForgotPasswordSendRequest request = new ForgotPasswordSendRequest();
        request.setEmail("email@email.com");

        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO("USER"));
        Mockito.when(contentDAO.queryContent(Mockito.any())).thenReturn(mockContentDO());

        ForgotPasswordSendResult result = forgotPasswordFacade.send(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void forgotPasswordQueryTest_SUCCESS() {
        ForgotPasswordQueryRequest request = new ForgotPasswordQueryRequest();
        request.setUuid("uuid");

        Mockito.when(resetPasswordDAO.query(Mockito.any())).thenReturn(mockResetPasswordDO());

        ForgotPasswordQueryResult result = forgotPasswordFacade.query(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    private ResetPasswordDO mockResetPasswordDO() {
        ResetPasswordDO resetPasswordDO = new ResetPasswordDO();
        resetPasswordDO.setEmail("email");

        return resetPasswordDO;
    }
}
