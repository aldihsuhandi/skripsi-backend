/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.request.UserDAORequest;
import id.thesis.shumishumi.foundation.model.result.UserDO;

import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserDAO.java, v 0.1 2022‐12‐26 7:28 AM Aldih Suhandi Exp $$
 */
public interface UserDAO {
    void create(UserDAORequest daoRequest);

    void update(UserDAORequest daoRequest);

    void updateProfilePicture(UserDAORequest daoRequest);

    void changeRole(UserDAORequest daoRequest);

    void updateReview(String userId, double review);

    UserDO queryById(UserDAORequest daoRequest);

    List<UserDO> queryByIds(List<UserDAORequest> userDAORequests);

    UserDO queryByEmail(UserDAORequest daoRequest);

    UserDO queryByPhoneNumber(UserDAORequest daoRequest);

    UserDO queryByUsername(UserDAORequest daoRequest);

    List<UserDO> queryAll();

    void emailEncrypt(String uuid, String email);

    String emailDecrypt(String uuid);
}
