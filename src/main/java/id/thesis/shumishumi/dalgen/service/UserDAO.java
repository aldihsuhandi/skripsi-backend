/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.dalgen.service;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.dalgen.model.request.UserDAORequest;
import id.thesis.shumishumi.dalgen.model.result.UserDO;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: UserDAO.java, v 0.1 2022‐12‐26 7:28 AM Aldih Suhandi Exp $$
 */
public interface UserDAO {
    void create(UserDAORequest daoRequest) throws ShumishumiException;

    void update(UserDAORequest daoRequest) throws ShumishumiException;

    void updateProfilePicture(UserDAORequest daoRequest) throws ShumishumiException;

    UserDO queryById(UserDAORequest daoRequest);

    UserDO queryByEmail(UserDAORequest daoRequest);

    UserDO queryByPhoneNumber(UserDAORequest daoRequest);
}
