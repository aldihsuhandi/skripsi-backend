/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Blob;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: UserDO.java, v 0.1 2022‐12‐26 2:21 PM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class UserDO implements Serializable {
    private static final long serialVersionUID = 6682459763308624451L;

    private String userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String roleId;
    private Blob profilePicture;
    private String password;
}
