package id.thesis.shumishumi.web.controller;

import id.thesis.shumishumi.common.model.form.otp.OTPSendForm;
import id.thesis.shumishumi.common.model.form.otp.OTPValidateForm;
import id.thesis.shumishumi.core.callback.ControllerCallback;
import id.thesis.shumishumi.core.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.facade.api.OTPFacade;
import id.thesis.shumishumi.facade.request.otp.OTPSendRequest;
import id.thesis.shumishumi.facade.request.otp.OTPValidateRequest;
import id.thesis.shumishumi.facade.result.otp.OTPSendResult;
import id.thesis.shumishumi.facade.result.otp.OTPValidateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
public class OTPController extends BaseController {
    @Autowired
    private OTPFacade otpFacade;

    @PostMapping("/send")
    public ResponseEntity<OTPSendResult> send(@RequestHeader HttpHeaders headers, @RequestBody OTPSendForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<OTPSendResult, OTPSendRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public OTPSendRequest composeRequest() {
                OTPSendRequest request = new OTPSendRequest();
                request.setEmail(form.getEmail());
                request.setOtpType(form.getOtpType());

                return request;
            }

            @Override
            public OTPSendResult doProcess(OTPSendRequest request) {
                return otpFacade.sendOtp(request);
            }
        });
    }

    @PostMapping("/validate")
    public ResponseEntity<OTPValidateResult> validate(@RequestHeader HttpHeaders headers, @RequestBody OTPValidateForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<OTPValidateResult, OTPValidateRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public OTPValidateRequest composeRequest() {
                OTPValidateRequest request = new OTPValidateRequest();
                request.setOtp(form.getOtp());
                request.setOtpType(form.getOtpType());
                request.setEmail(form.getEmail());

                return request;
            }

            @Override
            public OTPValidateResult doProcess(OTPValidateRequest request) {
                return otpFacade.validateOtp(request);
            }
        });
    }
}
