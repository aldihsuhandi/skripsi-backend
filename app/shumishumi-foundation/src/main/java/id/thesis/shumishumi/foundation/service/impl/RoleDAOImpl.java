package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.request.RoleDAORequest;
import id.thesis.shumishumi.foundation.model.result.RoleDO;
import id.thesis.shumishumi.foundation.repository.RoleRepository;
import id.thesis.shumishumi.foundation.service.RoleDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleDAOImpl implements RoleDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public RoleDO queryById(RoleDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("roleDAO#queryById[request=%s]", daoRequest));

        RoleDO result;
        try {
            result = roleRepository.findById(daoRequest.getRoleId()).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("roleDAO#queryById[result=%s]", result));
        return result;
    }
}
