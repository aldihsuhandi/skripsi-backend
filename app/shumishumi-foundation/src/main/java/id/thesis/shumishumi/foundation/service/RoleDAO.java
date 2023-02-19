package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.request.RoleDAORequest;
import id.thesis.shumishumi.foundation.model.result.RoleDO;

public interface RoleDAO {
    RoleDO queryById(RoleDAORequest daoRequest);
}
