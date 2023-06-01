/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.facade.model.context;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.Map;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserUpdateContext.java, v 0.1 2022‐12‐26 8:23 AM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class UserUpdateContext {
    private String username;
    private String email;
    private String phoneNumber;
    private String profilePicture;
    private String password;
    private String roleId;
    private String gender;
    private Date dateOfBirth;
    private Map<String, String> location;
    private Boolean isDeleted;
    private Boolean isActive;
    private Map<String, String> extendInfo;
}
