package id.thesis.shumishumi.dalgen.converter;

import id.thesis.shumishumi.dalgen.model.request.RoleDAORequest;

public class RoleDAORequestConverter {
    public static RoleDAORequest toDAORequest(String roleId) {
        RoleDAORequest daoRequest = new RoleDAORequest();
        daoRequest.setRoleId(roleId);

        return daoRequest;
    }
}
