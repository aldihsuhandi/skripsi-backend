package id.thesis.shumishumi.rest.controller;

import id.thesis.shumishumi.core.facade.OTPFacade;
import id.thesis.shumishumi.process.callback.ControllerCallback;
import id.thesis.shumishumi.process.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.rest.request.HtmlRequest;
import id.thesis.shumishumi.rest.request.otp.OTPSendRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import id.thesis.shumishumi.rest.result.otp.OTPSendResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
public class OTPController extends BaseController {
    @Autowired
    private OTPFacade otpFacade;

    @PostMapping("/send")
    public OTPSendResult send(@RequestBody HtmlRequest<OTPSendRequest> request) {
        return (OTPSendResult) ControllerCallbackSupport.process(request.getHead(), request.getBody(), new ControllerCallback() {
            @Override
            public void authCheck() {
                authenticate(request.getHead());
            }

            @Override
            public BaseResult initResult() {
                return new OTPSendResult();
            }

            @Override
            public BaseResult doProcess() {
                return otpFacade.sendOtp(request.getBody());
            }
        });
    }
}
