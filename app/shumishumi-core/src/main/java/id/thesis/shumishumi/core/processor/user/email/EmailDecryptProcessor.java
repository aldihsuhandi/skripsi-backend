package id.thesis.shumishumi.core.processor.user.email;

import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.user.email.EmailDecryptRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.user.email.EmailDecryptResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailDecryptProcessor implements BaseProcessor {

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        EmailDecryptRequest request = (EmailDecryptRequest) baseRequest;
        EmailDecryptResult result = (EmailDecryptResult) baseResult;

        String email = userService.emailDecrypt(request.getUuid());
        AssertUtil.isExpected(StringUtils.isNotEmpty(email), "result is not expected", ShumishumiErrorCodeEnum.SYSTEM_ERROR);

        result.setEmail(email);
    }
}
