/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.request.user.UserCreateInnerRequest;
import id.thesis.shumishumi.common.model.request.user.UserUpdateInnerRequest;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.util.converter.ViewObjectConverter;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.dalgen.converter.UserDAORequestConverter;
import id.thesis.shumishumi.dalgen.model.request.UserDAORequest;
import id.thesis.shumishumi.dalgen.service.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: UserServiceImpl.java, v 0.1 2022‐12‐26 7:27 AM Aldih Suhandi Exp $$
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Override
    public void register(UserCreateInnerRequest request) throws ShumishumiException {
        UserDAORequest daoRequest = UserDAORequestConverter.toDAORequest(request);
        userDAO.create(daoRequest);
    }

    @Override
    public void update(UserUpdateInnerRequest request) throws ShumishumiException {
        UserDAORequest daoRequest = UserDAORequestConverter.toDAORequest(request);
        userDAO.update(daoRequest);
    }

    @Override
    public void updateProfilePicture(UserUpdateInnerRequest request) throws ShumishumiException {
        UserDAORequest daoRequest = UserDAORequestConverter.toDAORequest(request);
        userDAO.updateProfilePicture(daoRequest);
    }

    @Override
    public UserVO queryById(String userId) {
        UserDAORequest daoRequest = UserDAORequestConverter.toDAORequest(DatabaseConst.USER_ID, userId);
        return ViewObjectConverter.toViewObject(userDAO.queryById(daoRequest));
    }

    @Override
    public UserVO queryByEmail(String email) {
        UserDAORequest daoRequest = UserDAORequestConverter.toDAORequest(DatabaseConst.EMAIL, email);
        return ViewObjectConverter.toViewObject(userDAO.queryByEmail(daoRequest));
    }

    @Override
    public UserVO queryByPhonenumber(String phoneNumber) {
        UserDAORequest daoRequest = UserDAORequestConverter.toDAORequest(DatabaseConst.PHONE_NUMBER, phoneNumber);
        return ViewObjectConverter.toViewObject(userDAO.queryByPhoneNumber(daoRequest));
    }
}
