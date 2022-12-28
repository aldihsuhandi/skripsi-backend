package id.thesis.shumishumi.dalgen.service;

import id.thesis.shumishumi.dalgen.model.request.RoleDAORequest;
import id.thesis.shumishumi.dalgen.model.result.RoleDO;

public interface RoleDAO {
    RoleDO queryById(RoleDAORequest daoRequest);
}
