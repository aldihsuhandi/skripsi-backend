package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.request.OTPDAORequest;
import id.thesis.shumishumi.foundation.model.result.OtpDO;

public interface OtpDAO {
    void insert(OTPDAORequest request);

    OtpDO query(OTPDAORequest request);

    void deactivate(OTPDAORequest request);

    void deactivate();
}
