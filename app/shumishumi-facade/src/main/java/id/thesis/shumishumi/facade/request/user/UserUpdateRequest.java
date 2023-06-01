/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.facade.request.user;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.Map;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserUpdateRequest.java, v 0.1 2022‐12‐27 1:43 PM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class UserUpdateRequest extends BaseRequest {
    private static final long serialVersionUID = -6612537427015638235L;

    private String oldPassword;
    private String username;
    private String email;
    private String phoneNumber;
    private MultipartFile profilePicture;
    private String gender;
    private Date dateOfBirth;
    private Map<String, String> location;
    private String password;
    private String confirmPassword;
    private boolean isDeleted;
    private boolean isActive;
    private Map<String, String> extendInfo;
}
