/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.request.UserDAORequest;
import id.thesis.shumishumi.foundation.model.result.EmailEncryptDO;
import id.thesis.shumishumi.foundation.model.result.UserDO;
import id.thesis.shumishumi.foundation.repository.EmailEncryptRepository;
import id.thesis.shumishumi.foundation.repository.UserRepository;
import id.thesis.shumishumi.foundation.service.UserDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserDAOImpl.java, v 0.1 2022‐12‐26 7:30 AM Aldih Suhandi Exp $$
 */
@Service
public class UserDAOImpl implements UserDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailEncryptRepository emailEncryptRepository;

    @Override
    public void create(UserDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("userDAO#create[request=%s]", daoRequest.toString()));
        UserDO userDO = convertFromDAORequest(daoRequest);

        try {
            userRepository.save(userDO);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void update(UserDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("userDAO#update[request=%s]", daoRequest.toString()));
        UserDO userDO = convertFromDAORequest(daoRequest);

        try {
            userRepository.save(userDO);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void updateProfilePicture(UserDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("userDAO#updateProfilePicture[request=%s]", daoRequest.toString()));
        try {
            userRepository.updateProfilePicture(daoRequest.getUserId(), daoRequest.getProfilePicture());
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void changeRole(UserDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("userDAO#changeRole[request=%s]", daoRequest.toString()));
        try {
            userRepository.changeRoleUser(daoRequest.getUserId(), daoRequest.getRoleId());
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public UserDO queryById(UserDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("userDAO#queryById[request=%s]", daoRequest.toString()));

        UserDO result;
        try {
            result = userRepository.findById(daoRequest.getUserId()).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("userDAO#queryById[result=%s]", result));
        return result;
    }

    @Override
    public List<UserDO> queryByIds(List<UserDAORequest> userDAORequests) {
        LogUtil.info(DALGEN_LOGGER, String.format("userDAO#queryByIds[request=%s]", userDAORequests.toString()));

        List<String> userIds = userDAORequests.stream().map(UserDAORequest::getUserId).collect(Collectors.toList());
        List<UserDO> userDOS;

        try {
            userDOS = userRepository.findByIds(userIds);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("userDAo#queryByIds[result=%s]", userDOS));

        return userDOS;
    }

    @Override
    public UserDO queryByEmail(UserDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("userDAO#queryByEmail[request=%s]", daoRequest.toString()));

        UserDO userDO;
        try {
            userDO = userRepository.findByEmail(daoRequest.getEmail()).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("userDAO#queryByEmail[result=%s]", userDO));
        return userDO;
    }

    @Override
    public UserDO queryByPhoneNumber(UserDAORequest daoRequest) {
        LogUtil.info(DALGEN_LOGGER, String.format("userDAO#queryByPhoneNumber[request=%s]", daoRequest.toString()));

        UserDO userDO;
        try {
            userDO = userRepository.findByPhoneNumber(daoRequest.getPhoneNumber()).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("userDAO#queryByPhoneNumber[result=%s]", userDO));
        return userDO;
    }

    @Override
    public List<UserDO> queryAll() {
        LogUtil.info(DALGEN_LOGGER, "userDAO#queryAll");

        List<UserDO> result;
        try {
            result = userRepository.findAll();
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("userDAO#queryAll[request=%s]", result));
        return result;
    }

    @Override
    public void emailEncrypt(String uuid, String email) {
        LogUtil.info(DALGEN_LOGGER, String.format("userDAO#emailEncrypt[uuid=%s,email=%s]", uuid, email));

        try {
            EmailEncryptDO encryptDO = new EmailEncryptDO();
            encryptDO.setUuid(uuid);
            encryptDO.setEmail(email);

            emailEncryptRepository.save(encryptDO);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public String emailDecrypt(String uuid) {
        LogUtil.info(DALGEN_LOGGER, String.format("userDAO#emailDecrypt[uuid=%s]", uuid));
        String email = "";
        try {
            EmailEncryptDO encryptDO = emailEncryptRepository.findById(uuid).orElse(null);
            if (encryptDO != null) {
                email = encryptDO.getEmail();
            }
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("userDAO#emailDescrypt[email=%s]", email));
        return email;
    }

    private UserDO convertFromDAORequest(UserDAORequest request) {
        if (request == null) {
            return null;
        }

        UserDO userDO = new UserDO();
        userDO.setUserId(request.getUserId());
        userDO.setUsername(request.getUsername());
        userDO.setEmail(request.getEmail());
        userDO.setPhoneNumber(request.getPhoneNumber());
        userDO.setRoleId(request.getRoleId());
        userDO.setActive(request.isActive());
        userDO.setDeleted(request.isDeleted());
        userDO.setProfilePicture(request.getProfilePicture());
        userDO.setPassword(request.getPassword());

        return userDO;
    }
}
