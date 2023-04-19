/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.request.user.RoleChangeInnerRequest;
import id.thesis.shumishumi.common.model.request.user.UserCreateInnerRequest;
import id.thesis.shumishumi.common.model.request.user.UserUpdateInnerRequest;
import id.thesis.shumishumi.common.service.RoleService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.core.converter.ViewObjectConverter;
import id.thesis.shumishumi.core.fetch.UserFetchService;
import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.facade.model.viewobject.RoleVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.foundation.converter.UserDAORequestConverter;
import id.thesis.shumishumi.foundation.model.request.UserDAORequest;
import id.thesis.shumishumi.foundation.model.result.UserDO;
import id.thesis.shumishumi.foundation.service.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserServiceImpl.java, v 0.1 2022‐12‐26 7:27 AM Aldih Suhandi Exp $$
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private UserFetchService userFetchService;

    @Autowired
    private RoleService roleService;

    @Override
    public void register(UserCreateInnerRequest request) {
        UserDAORequest daoRequest = UserDAORequestConverter.toDAORequest(request);
        userDAO.create(daoRequest);
    }

    @Override
    public void update(UserUpdateInnerRequest request) {
        UserDAORequest daoRequest = UserDAORequestConverter.toDAORequest(request);
        userDAO.update(daoRequest);
    }

    @Override
    public void roleChange(RoleChangeInnerRequest request) {
        UserDAORequest daoRequest = UserDAORequestConverter.toDAORequest(request);
        userDAO.changeRole(daoRequest);
    }

    @Override
    public void updateProfilePicture(UserUpdateInnerRequest request) {
        UserDAORequest daoRequest = new UserDAORequest();
        daoRequest.setUserId(request.getUserId());
        daoRequest.setProfilePicture(request.getUserUpdateContext().getProfilePicture());

        userDAO.updateProfilePicture(daoRequest);
    }

    @Override
    public UserVO queryById(String userId, boolean useCache) {
        UserVO userVO = queryFromCache(useCache, userId, DatabaseConst.USER_ID);

        if (userVO == null) {
            UserDAORequest daoRequest = UserDAORequestConverter.toDAORequest(DatabaseConst.USER_ID, userId);
            UserDO userDO = userDAO.queryById(daoRequest);
            userVO = ViewObjectConverter.toViewObject(userDO);

            composeRoleVO(userDO, userVO);

            userFetchService.putToCache(userVO);
        }

        return userVO;
    }

    @Override
    public UserVO queryByEmail(String email, boolean useCache) {
        UserVO userVO = queryFromCache(useCache, email, DatabaseConst.EMAIL);

        if (userVO == null) {
            UserDAORequest daoRequest = UserDAORequestConverter.toDAORequest(DatabaseConst.EMAIL, email);
            UserDO userDO = userDAO.queryByEmail(daoRequest);
            userVO = ViewObjectConverter.toViewObject(userDO);

            composeRoleVO(userDO, userVO);

            userFetchService.putToCache(userVO);
        }

        return userVO;
    }

    @Override
    public UserVO queryByPhoneNumber(String phoneNumber, boolean useCache) {
        UserVO userVO = queryFromCache(useCache, phoneNumber, DatabaseConst.PHONE_NUMBER);

        if (userVO == null) {
            UserDAORequest daoRequest = UserDAORequestConverter.toDAORequest(DatabaseConst.PHONE_NUMBER, phoneNumber);
            UserDO userDO = userDAO.queryByPhoneNumber(daoRequest);
            userVO = ViewObjectConverter.toViewObject(userDO);

            composeRoleVO(userDO, userVO);

            userFetchService.putToCache(userVO);
        }

        return userVO;
    }

    @Override
    public void refreshCache(List<String> userIds, boolean refreshAll) {
        List<UserVO> userVOS = refreshCacheAll(refreshAll);
        if (userVOS == null) {
            List<UserDAORequest> daoRequests = userIds.stream().map(userId ->
                    UserDAORequestConverter.toDAORequest(DatabaseConst.USER_ID, userId)).collect(Collectors.toList());

            List<UserDO> userDOS = userDAO.queryByIds(daoRequests);
            userVOS = userDOS.stream().map(userDO -> {
                UserVO userVO = ViewObjectConverter.toViewObject(userDO);
                composeRoleVO(userDO, userVO);

                return userVO;
            }).collect(Collectors.toList());
        }

        for (UserVO userVO : userVOS) {
            userFetchService.putToCache(userVO);
        }
    }

    private UserVO queryFromCache(boolean useCache, String key, String identifier) {
        if (!useCache) {
            return null;
        }
        return userFetchService.fetchFromCache(key, identifier);
    }

    private List<UserVO> refreshCacheAll(boolean refreshAll) {
        if (!refreshAll) {
            return null;
        }

        List<UserDO> userDOS = userDAO.queryAll();
        return userDOS.stream().map(userDO -> {
            UserVO vo = ViewObjectConverter.toViewObject(userDO);
            composeRoleVO(userDO, vo);

            return vo;
        }).collect(Collectors.toList());
    }

    private void composeRoleVO(UserDO userDO, UserVO userVO) {
        if (userDO == null) {
            return;
        }
        RoleVO roleVO = roleService.queryById(userDO.getRoleId());
        userVO.setRoleVO(roleVO);
    }
}
