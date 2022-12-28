/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.request.user.UserCreateInnerRequest;
import id.thesis.shumishumi.common.model.request.user.UserUpdateInnerRequest;
import id.thesis.shumishumi.common.model.viewobject.UserVO;

import java.util.List;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: UserService.java, v 0.1 2022‐12‐26 7:09 AM Aldih Suhandi Exp $$
 */
public interface UserService {
    void register(UserCreateInnerRequest request) throws ShumishumiException;

    void update(UserUpdateInnerRequest request) throws ShumishumiException;

    void updateProfilePicture(UserUpdateInnerRequest request) throws ShumishumiException;

    UserVO queryById(String userId, boolean useCache);

    UserVO queryByEmail(String email, boolean useCache);

    UserVO queryByPhoneNumber(String phoneNumber, boolean useCache);

    void refreshCache(List<String> userIds, boolean refreshAll);
}
