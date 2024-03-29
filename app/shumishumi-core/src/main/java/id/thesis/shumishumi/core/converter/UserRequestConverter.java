/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.converter;

import id.thesis.shumishumi.common.model.request.user.UserCreateInnerRequest;
import id.thesis.shumishumi.common.model.request.user.UserUpdateInnerRequest;
import id.thesis.shumishumi.facade.model.context.UserUpdateContext;
import id.thesis.shumishumi.facade.model.enumeration.UserRolesEnum;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.facade.request.user.UserRegisterRequest;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserRequestConverter.java, v 0.1 2022‐12‐26 8:16 AM Aldih Suhandi Exp $$
 */
public class UserRequestConverter {
    public static UserCreateInnerRequest toInnerRequest(UserRegisterRequest request, String userId) {
        UserCreateInnerRequest innerRequest = new UserCreateInnerRequest();
        innerRequest.setUserId(userId);
        innerRequest.setEmail(request.getEmail());
        innerRequest.setPhoneNumber(request.getPhoneNumber());
        innerRequest.setUsername(request.getUsername());
        innerRequest.setPassword(request.getPassword());
        innerRequest.setRoleId(UserRolesEnum.USER.getUserRoleId());
        innerRequest.setDateOfBirth(request.getDateOfBirth());
        innerRequest.setGender(request.getGender());

        return innerRequest;
    }

    public static UserUpdateInnerRequest toInnerRequest(String userId, String profilePicture) {
        UserUpdateInnerRequest innerRequest = new UserUpdateInnerRequest();
        UserUpdateContext userUpdateContext = new UserUpdateContext();

        userUpdateContext.setProfilePicture(profilePicture);
        innerRequest.setUserId(userId);
        innerRequest.setUserUpdateContext(userUpdateContext);

        return innerRequest;
    }

    public static UserUpdateInnerRequest toInnerRequest(UserVO userVO, UserUpdateContext updateContext) {
        UserUpdateInnerRequest innerRequest = new UserUpdateInnerRequest();
        innerRequest.setUserVO(userVO);
        innerRequest.setUserUpdateContext(updateContext);

        return innerRequest;
    }
}
