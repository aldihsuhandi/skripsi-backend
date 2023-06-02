package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.request.OTPDAORequest;
import id.thesis.shumishumi.foundation.model.result.OtpDO;
import id.thesis.shumishumi.foundation.repository.OtpRepository;
import id.thesis.shumishumi.foundation.service.OtpDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class OtpDAOImpl implements OtpDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private OtpRepository otpRepository;

    @Override
    public void insert(OTPDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("otpDAO#insert[request=%s]", request));

        OtpDO otpDO = convertFromRequest(request);
        try {
            otpRepository.save(otpDO);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public OtpDO query(OTPDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("otpDAO#query[request=%s]", request));

        OtpDO exampleOtp = new OtpDO();
        exampleOtp.setOtp(request.getOtp());
        exampleOtp.setActive(request.isActive());
        exampleOtp.setTypeId(request.getTypeId());

        Example<OtpDO> exampleObject = Example.of(exampleOtp);

        OtpDO result;
        try {
            result = otpRepository.findOne(exampleObject).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("otpDAO#query[result=%s]", result));
        return result;
    }

    @Override
    public void deactivate(OTPDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("otpDAO#deactivate[request=%s]", request));

        try {
            otpRepository.deactivate(request.getOtpId());
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void deactivate() {
        LogUtil.info(DALGEN_LOGGER, "otpDAO#deactivate[]");
        try {
            otpRepository.deactivate();
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    private OtpDO convertFromRequest(OTPDAORequest request) {
        OtpDO otpDO = new OtpDO();
        otpDO.setOtpId(request.getOtpId());
        otpDO.setOtp(request.getOtp());
        otpDO.setOtpDt(request.getOtpDt());
        otpDO.setTypeId(request.getTypeId());
        otpDO.setEmail(request.getEmail());
        otpDO.setActive(request.isActive());

        return otpDO;
    }
}
