/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.common.model.request.user.RoleChangeInnerRequest;
import id.thesis.shumishumi.common.model.request.user.UserCreateInnerRequest;
import id.thesis.shumishumi.common.model.request.user.UserUpdateInnerRequest;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;

import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserService.java, v 0.1 2022‐12‐26 7:09 AM Aldih Suhandi Exp $$
 */
public interface UserService {
    void register(UserCreateInnerRequest request);

    void update(UserUpdateInnerRequest request);

    void roleChange(RoleChangeInnerRequest request);

    void updateProfilePicture(UserUpdateInnerRequest request);

    UserVO queryById(String userId, boolean useCache);

    UserVO queryByEmail(String email, boolean useCache);

    UserVO queryByPhoneNumber(String phoneNumber, boolean useCache);

    UserVO queryByUsername(String username, boolean cache);

    void refreshCache(List<String> userIds, boolean refreshAll);

    void clearCache();

    String emailEncrypt(String email);

    String emailDecrypt(String uuid);
}
