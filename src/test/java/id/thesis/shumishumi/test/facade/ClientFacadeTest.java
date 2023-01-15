package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.core.facade.ClientFacade;
import id.thesis.shumishumi.dalgen.model.result.ClientDO;
import id.thesis.shumishumi.rest.request.client.ClientAuthRequest;
import id.thesis.shumishumi.rest.result.client.ClientAuthResult;
import id.thesis.shumishumi.test.TestBase;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

public class ClientFacadeTest extends TestBase {

    @Autowired
    private ClientFacade clientFacade;

    @Test
    public void authenticateTest_SUCCESS() {
        ClientAuthRequest request = new ClientAuthRequest();
        String clientId = "clientId";
        String clientSecret = "clientSecret";
        request.setClientId(clientId);
        request.setClientSecret(clientSecret);

        Mockito.when(clientDAO.queryById(Mockito.any())).thenReturn(mockClientDO(clientId, clientSecret));

        ClientAuthResult result = clientFacade.authenticate(request);

        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }

    @Test
    public void authenticateTest_FAILED_client_not_exist() {
        ClientAuthRequest request = new ClientAuthRequest();
        String clientId = "clientId";
        String clientSecret = "clientSecret";
        request.setClientId(clientId);
        request.setClientSecret(clientSecret);

        Mockito.when(clientDAO.queryById(Mockito.any())).thenReturn(null);

        ClientAuthResult result = clientFacade.authenticate(request);

        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.OAUTH_ERROR.getErrorCode());
    }

    @Test
    public void authenticateTest_FAILED_client_secret_failed() {
        ClientAuthRequest request = new ClientAuthRequest();
        String clientId = "clientId";
        String clientSecret = "clientSecret";
        request.setClientId(clientId);
        request.setClientSecret(clientSecret);

        Mockito.when(clientDAO.queryById(Mockito.any())).thenReturn(mockClientDO(clientId, clientSecret + "2"));

        ClientAuthResult result = clientFacade.authenticate(request);

        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.OAUTH_ERROR.getErrorCode());
    }

    private ClientDO mockClientDO(String clientId, String clientSecret) {
        ClientDO clientDO = new ClientDO();
        clientDO.setClientId(clientId);
        clientDO.setClientSecret(clientSecret);

        return clientDO;
    }
}
