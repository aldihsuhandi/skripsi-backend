package id.thesis.shumishumi.core.processor.user.email;

import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.user.email.EmailEncryptRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.user.email.EmailEncryptResult;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailEncryptProcessor implements BaseProcessor {

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        EmailEncryptRequest request = (EmailEncryptRequest) baseRequest;
        EmailEncryptResult result = (EmailEncryptResult) baseResult;

        String uuid = userService.emailEncrypt(request.getEmail());

        result.setUuid(uuid);
    }
}
