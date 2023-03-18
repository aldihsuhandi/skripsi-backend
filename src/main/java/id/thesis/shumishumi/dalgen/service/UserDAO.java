/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.dalgen.service;

import id.thesis.shumishumi.dalgen.model.request.UserDAORequest;
import id.thesis.shumishumi.dalgen.model.result.UserDO;

import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserDAO.java, v 0.1 2022‐12‐26 7:28 AM Aldih Suhandi Exp $$
 */
public interface UserDAO {
    void create(UserDAORequest daoRequest);

    void update(UserDAORequest daoRequest);

    void updateProfilePicture(UserDAORequest daoRequest);

    UserDO queryById(UserDAORequest daoRequest);

    List<UserDO> queryByIds(List<UserDAORequest> userDAORequests);

    UserDO queryByEmail(UserDAORequest daoRequest);

    UserDO queryByPhoneNumber(UserDAORequest daoRequest);

    List<UserDO> queryAll();

}
