/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.foundation.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserDAORequest.java, v 0.1 2022‐12‐26 7:29 AM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class UserDAORequest implements Serializable {
    private String userId;
    private String roleId;
    private String email;
    private String username;
    private String phoneNumber;
    private String profilePicture;
    private String password;
    private int age;
    private String gender;
    private String location;
    private boolean isActive;
    private boolean isDeleted;
    private Date gmtCreate;
    private Date gmtModified;
}
