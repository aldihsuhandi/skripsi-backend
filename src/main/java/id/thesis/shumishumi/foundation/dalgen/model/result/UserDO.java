/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.foundation.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserDO.java, v 0.1 2022‐12‐26 2:21 PM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class UserDO extends BaseDO {
    private String userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String roleId;
    private boolean isActive;
    private boolean isDeleted;
    private Blob profilePicture;
    private String password;
}
