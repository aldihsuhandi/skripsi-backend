/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.foundation.converter;

import id.thesis.shumishumi.common.model.request.user.RoleChangeInnerRequest;
import id.thesis.shumishumi.common.model.request.user.UserCreateInnerRequest;
import id.thesis.shumishumi.common.model.request.user.UserUpdateInnerRequest;
import id.thesis.shumishumi.facade.model.context.UserUpdateContext;
import id.thesis.shumishumi.foundation.model.request.UserDAORequest;

import java.util.Date;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserDAORequestConverter.java, v 0.1 2022‐12‐26 7:48 AM Aldih Suhandi Exp $$
 */
public class UserDAORequestConverter {
    public static UserDAORequest toDAORequest(UserCreateInnerRequest innerRequest) {
        UserDAORequest userDAORequest = new UserDAORequest();
        userDAORequest.setUserId(innerRequest.getUserId());
        userDAORequest.setEmail(innerRequest.getEmail());
        userDAORequest.setPhoneNumber(innerRequest.getPhoneNumber());
        userDAORequest.setUsername(innerRequest.getUsername());
        userDAORequest.setPassword(innerRequest.getPassword());

        return userDAORequest;
    }

    public static UserDAORequest toDAORequest(UserUpdateInnerRequest request) {
        UserDAORequest userDAORequest = new UserDAORequest();
        UserUpdateContext updateContext = request.getUserUpdateContext();
        userDAORequest.setUserId(request.getUserId());
        userDAORequest.setEmail(updateContext.getEmail());
        userDAORequest.setPhoneNumber(updateContext.getPhoneNumber());
        userDAORequest.setUsername(updateContext.getUsername());
        userDAORequest.setPassword(updateContext.getPassword());
        userDAORequest.setProfilePicture(updateContext.getProfilePicture());
        userDAORequest.setActive(updateContext.getIsActive());
        userDAORequest.setDeleted(updateContext.getIsDeleted());
        userDAORequest.setGmtModified(new Date());

        return userDAORequest;
    }

    public static UserDAORequest toDAORequest(String key, String value) {
        UserDAORequest userDAORequest = new UserDAORequest();
        switch (key) {
            case "user_id":
                userDAORequest.setUserId(value);
                break;
            case "email":
                userDAORequest.setEmail(value);
                break;
            case "phone_number":
                userDAORequest.setPhoneNumber(value);
                break;
        }

        return userDAORequest;
    }

    public static UserDAORequest toDAORequest(RoleChangeInnerRequest inner) {
        if (inner == null) {
            return null;
        }

        UserDAORequest request = new UserDAORequest();
        request.setUserId(inner.getUserId());
        request.setRoleId(inner.getUserRole());
        request.setGmtModified(new Date());

        return request;
    }
}
