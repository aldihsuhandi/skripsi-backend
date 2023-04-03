package id.thesis.shumishumi.foundation.dalgen.converter;

import id.thesis.shumishumi.foundation.dalgen.model.request.RoleDAORequest;

public class RoleDAORequestConverter {
    public static RoleDAORequest toDAORequest(String roleId) {
        RoleDAORequest daoRequest = new RoleDAORequest();
        daoRequest.setRoleId(roleId);

        return daoRequest;
    }
}
