package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.common.util.converter.ViewObjectConverter;
import id.thesis.shumishumi.common.model.viewobject.RoleVO;
import id.thesis.shumishumi.core.service.RoleService;
import id.thesis.shumishumi.foundation.dalgen.converter.RoleDAORequestConverter;
import id.thesis.shumishumi.foundation.dalgen.model.request.RoleDAORequest;
import id.thesis.shumishumi.foundation.dalgen.service.RoleDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDAO roleDAO;

    @Override
    public RoleVO queryById(String roleId) {
        RoleDAORequest daoRequest = RoleDAORequestConverter.toDAORequest(roleId);
        return ViewObjectConverter.toViewObject(roleDAO.queryById(daoRequest));
    }
}
