package id.thesis.shumishumi.test.controller;

import id.thesis.shumishumi.common.model.context.ResultContext;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.core.facade.ClientFacade;
import id.thesis.shumishumi.core.request.client.ClientAuthRequest;
import id.thesis.shumishumi.core.result.client.ClientAuthResult;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class ControllerTestBase {
    @MockBean
    protected ClientFacade clientFacade;

    @BeforeEach
    public void setupClient() {
        ClientAuthResult result = new ClientAuthResult();
        result.setResultContext(mockResultContext(ShumishumiErrorCodeEnum.SUCCESS));

        Mockito.when(clientFacade.authenticate(Mockito.any(ClientAuthRequest.class))).thenReturn(result);
    }

    protected HttpHeaders mockHeaders() {
        String sessionId = "sessionId";
        String clientId = "clientId";
        String clientSecret = "clientSecret";

        HttpHeaders headers = new HttpHeaders();
        headers.set("sessionId", sessionId);
        headers.set("clientId", clientId);
        headers.set("clientSecret", clientSecret);

        return headers;
    }

    protected ResultContext mockResultContext(ShumishumiErrorCodeEnum errorCode) {
        ResultContext resultContext = new ResultContext();
        resultContext.setResultMsg(errorCode.getErrorMsg());
        resultContext.setResultCode(errorCode.getErrorCode());
        resultContext.setSuccess(errorCode.equals(ShumishumiErrorCodeEnum.SUCCESS));

        return resultContext;
    }
}
