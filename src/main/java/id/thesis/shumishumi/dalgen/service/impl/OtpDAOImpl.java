package id.thesis.shumishumi.dalgen.service.impl;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.database.StatementBuilder;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.dalgen.model.mapper.OTPDOMapper;
import id.thesis.shumishumi.dalgen.model.request.OTPDAORequest;
import id.thesis.shumishumi.dalgen.model.result.OtpDO;
import id.thesis.shumishumi.dalgen.service.OtpDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class OtpDAOImpl implements OtpDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void insert(OTPDAORequest request) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_OTPS, DatabaseConst.STATEMENT_INSERT)
                .addValueStatement(DatabaseConst.OTP_ID)
                .addValueStatement(DatabaseConst.OTP)
                .addValueStatement(DatabaseConst.OTP_TYPE_ID)
                .addValueStatement(DatabaseConst.OTP_DT)
                .addValueStatement(DatabaseConst.EMAIL)
                .buildStatement();

        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setString(1, request.getOtpId());
                ps.setString(2, request.getOtp());
                ps.setString(3, request.getTypeId());
                ps.setTimestamp(4, new Timestamp(request.getOtpDt().getTime()));
                ps.setString(5, request.getEmail());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public OtpDO query(OTPDAORequest request) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_OTPS, DatabaseConst.STATEMENT_SELECT)
                .addSelectStatement(DatabaseConst.DATABASE_SELECT_ALL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.EMAIL, DatabaseConst.COMPARATOR_EQUAL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.OTP_TYPE_ID, DatabaseConst.COMPARATOR_EQUAL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.OTP, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        List<OtpDO> otpDOS = jdbcTemplate.query(statement, ps -> {
            ps.setString(1, request.getEmail());
            ps.setString(2, request.getTypeId());
            ps.setString(3, request.getOtp());
        }, new OTPDOMapper());

        if (otpDOS.isEmpty()) {
            return null;
        }

        return otpDOS.get(0);
    }

    @Override
    public void deactivate(OTPDAORequest request) {
        String statement = new StatementBuilder(DatabaseConst.TABLE_OTPS, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.IS_ACTIVE)
                .addSetStatement(DatabaseConst.GMT_MODIFIED)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.OTP_ID, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        Date currDate = new Date();
        int result;
        try {
            result = jdbcTemplate.update(statement, ps -> {
                ps.setBoolean(1, false);
                ps.setTimestamp(2, new Timestamp(currDate.getTime()));
                ps.setString(3, request.getOtpId());
            });
        } catch (Exception e) {
            throw new ShumishumiException(e.getCause().getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        AssertUtil.isExpected(result, 1, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public void deactivate() {
        String statement = new StatementBuilder(DatabaseConst.TABLE_OTPS, DatabaseConst.STATEMENT_UPDATE)
                .addSetStatement(DatabaseConst.IS_ACTIVE)
                .addSetStatement(DatabaseConst.GMT_MODIFIED)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.OTP_DT, DatabaseConst.COMPARATOR_LESSER_EQUAL)
                .addWhereStatement(DatabaseConst.APPEND_OPERATOR_AND, DatabaseConst.IS_ACTIVE, DatabaseConst.COMPARATOR_EQUAL)
                .buildStatement();

        Date currDate = new Date();
        jdbcTemplate.update(statement, ps -> {
            ps.setBoolean(1, false);
            ps.setTimestamp(2, new Timestamp(currDate.getTime()));
            ps.setTimestamp(3, new Timestamp(currDate.getTime()));
            ps.setBoolean(4, true);
        });
    }
}
