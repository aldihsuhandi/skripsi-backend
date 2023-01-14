/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.model.context;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: UserUpdateContext.java, v 0.1 2022‐12‐26 8:23 AM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class UserUpdateContext {
    private String username;
    private String email;
    private String phoneNumber;
    private Blob profilePicture;
    private String password;
    private Boolean isDeleted;
    private Boolean isActive;
}
