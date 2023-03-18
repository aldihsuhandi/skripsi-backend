/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserVO.java, v 0.1 2022‐12‐26 8:47 AM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class UserVO extends BaseVO {
    private String userId;
    private String username;
    private String email;
    private String phoneNumber;
    private Blob profilePicture;
    private boolean isActive;
    private boolean isDeleted;
    private String password;
    private RoleVO roleVO;
}
