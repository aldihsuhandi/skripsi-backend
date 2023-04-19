package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.service.RoleService;
import id.thesis.shumishumi.core.converter.ViewObjectConverter;
import id.thesis.shumishumi.facade.model.viewobject.RoleVO;
import id.thesis.shumishumi.foundation.converter.RoleDAORequestConverter;
import id.thesis.shumishumi.foundation.model.request.RoleDAORequest;
import id.thesis.shumishumi.foundation.service.RoleDAO;
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
