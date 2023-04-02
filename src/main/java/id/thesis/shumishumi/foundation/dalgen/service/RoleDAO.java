package id.thesis.shumishumi.foundation.dalgen.service;

import id.thesis.shumishumi.foundation.dalgen.model.request.RoleDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.result.RoleDO;

public interface RoleDAO {
    RoleDO queryById(RoleDAORequest daoRequest);
}
