package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.request.ResetPasswordDAORequest;
import id.thesis.shumishumi.foundation.model.result.ResetPasswordDO;
import id.thesis.shumishumi.foundation.repository.ResetPasswordRepository;
import id.thesis.shumishumi.foundation.service.ResetPasswordDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResetPasswordDAOImpl implements ResetPasswordDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private ResetPasswordRepository resetPasswordRepository;

    @Override
    public void insert(ResetPasswordDAORequest request) {
        LogUtil.info(LOGGER, String.format("resetPasswordDAO#insert[request=%s]", request));
        ResetPasswordDO reset = convertFromDAORequest(request);

        try {
            resetPasswordRepository.save(reset);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ResetPasswordDO query(String uuid) {
        LogUtil.info(LOGGER, String.format("resetPasswordDAO#query[uuid=%s]", uuid));

        ResetPasswordDO result;
        try {
            result = resetPasswordRepository.findActiveRequest(uuid).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("resetPasswordDAO#query[result=%s]", result));
        return result;
    }

    @Override
    public void invalidate(String uuid) {
        LogUtil.info(LOGGER, String.format("resetPasswordDAO#invalidate[uuid=%s]", uuid));
        try {
            resetPasswordRepository.invalidate(uuid);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    private ResetPasswordDO convertFromDAORequest(ResetPasswordDAORequest request) {
        if (request == null) {
            return null;
        }

        ResetPasswordDO reset = new ResetPasswordDO();
        reset.setUuid(request.getUuid());
        reset.setEmail(request.getEmail());
        reset.setExpiredTime(request.getExpiredTime());
        reset.setActive(true);

        return reset;
    }
}
