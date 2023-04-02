package id.thesis.shumishumi.foundation.dalgen.service;

import id.thesis.shumishumi.foundation.dalgen.model.request.OTPDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.result.OtpDO;

public interface OtpDAO {
    void insert(OTPDAORequest request);

    OtpDO query(OTPDAORequest request);

    void deactivate(OTPDAORequest request);

    void deactivate();
}
