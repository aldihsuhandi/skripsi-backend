package id.thesis.shumishumi.process.service;

import id.thesis.shumishumi.common.converter.ViewObjectConverter;
import id.thesis.shumishumi.common.model.viewobject.OtpVO;
import id.thesis.shumishumi.core.service.OTPService;
import id.thesis.shumishumi.dalgen.converter.OTPDAORequestConverter;
import id.thesis.shumishumi.dalgen.model.request.OTPDAORequest;
import id.thesis.shumishumi.dalgen.service.OtpDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OTPServiceImpl implements OTPService {

    @Autowired
    private OtpDAO otpDAO;

    @Override
    public void insert(String email, String type, String otp) {
        OTPDAORequest otpdaoRequest = OTPDAORequestConverter.toDAORequest(email, otp, type);
        otpDAO.insert(otpdaoRequest);
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
}
