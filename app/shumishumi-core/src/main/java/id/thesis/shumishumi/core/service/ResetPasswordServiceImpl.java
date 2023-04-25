package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.service.ResetPasswordService;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.foundation.model.request.ResetPasswordDAORequest;
import id.thesis.shumishumi.foundation.model.result.ResetPasswordDO;
import id.thesis.shumishumi.foundation.service.ResetPasswordDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Service
public class ResetPasswordServiceImpl implements ResetPasswordService {

    @Autowired
    private ResetPasswordDAO resetPasswordDAO;

    @Override
    public String resetPassword(String email) {
        ResetPasswordDAORequest reset = new ResetPasswordDAORequest();
        reset.setUuid(FunctionUtil.generateUUID());
        reset.setEmail(email);
        reset.setExpiredTime(Date.from(LocalDateTime.now().plus(Duration.
                of(10, ChronoUnit.MINUTES)).atZone(ZoneId.systemDefault()).toInstant()));
        reset.setActive(true);

        resetPasswordDAO.insert(reset);

        return reset.getUuid();
    }

    @Override
    public String queryResetPassword(String uuid) {
        ResetPasswordDO resetPasswordDO = resetPasswordDAO.query(uuid);
        if (resetPasswordDO == null) {
            return "";
        }

        return resetPasswordDO.getEmail();
    }

    @Override
    public void invalidateResetPassword(String uuid) {
        resetPasswordDAO.invalidate(uuid);
    }
}
