package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.request.ResetPasswordDAORequest;
import id.thesis.shumishumi.foundation.model.result.ResetPasswordDO;

public interface ResetPasswordDAO {
    void insert(ResetPasswordDAORequest request);

    ResetPasswordDO query(String uuid);

    void invalidate(String uuid);
}
