package id.thesis.shumishumi.core.process.service;

import id.thesis.shumishumi.common.converter.ViewObjectConverter;
import id.thesis.shumishumi.common.model.request.otp.OTPSendInnerRequest;
import id.thesis.shumishumi.common.model.viewobject.OtpVO;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.service.EmailService;
import id.thesis.shumishumi.core.service.OTPService;
import id.thesis.shumishumi.foundation.dalgen.converter.OTPDAORequestConverter;
import id.thesis.shumishumi.foundation.dalgen.model.request.OTPDAORequest;
import id.thesis.shumishumi.foundation.dalgen.service.OtpDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTPServiceImpl implements OTPService {

    @Autowired
    private OtpDAO otpDAO;

    @Autowired
    private EmailService emailService;

    @Override
    public void send(String email, String type) {
        String otp = FunctionUtil.generateOtp(5, true, true);
        OTPDAORequest otpdaoRequest = OTPDAORequestConverter.toDAORequest(email, otp, type);

        otpDAO.insert(otpdaoRequest);

        emailService.sendOtpEmail(composeEmailRequest(email, type, otp));
    }

    @Override
    public OtpVO query(String email, String type, String otp) {
        OTPDAORequest otpdaoRequest = OTPDAORequestConverter.toDAORequest(email, otp, type);
        return ViewObjectConverter.toViewObject(otpDAO.query(otpdaoRequest));
    }

    @Override
    public void deactivate(String otpId) {
        OTPDAORequest otpdaoRequest = OTPDAORequestConverter.toDAORequest(otpId);
        otpDAO.deactivate(otpdaoRequest);
    }

    @Override
    public void deactivate() {
        otpDAO.deactivate();
    }

    private OTPSendInnerRequest composeEmailRequest(String email, String type, String otp) {
        OTPSendInnerRequest request = new OTPSendInnerRequest();
        request.setOtp(otp);
        request.setOtpType(type);
        request.setEmail(email);

        return request;
    }
}
