package id.thesis.shumishumi.dalgen.service;

import id.thesis.shumishumi.dalgen.model.request.OTPDAORequest;
import id.thesis.shumishumi.dalgen.model.result.OtpDO;

public interface OtpDAO {
    void insert(OTPDAORequest request);

    OtpDO query(OTPDAORequest request);

    void deactivate(OTPDAORequest request);

    void deactivate();
}
