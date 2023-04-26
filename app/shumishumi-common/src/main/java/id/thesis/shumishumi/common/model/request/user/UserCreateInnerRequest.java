/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.model.request.user;

import id.thesis.shumishumi.common.model.request.BaseInnerRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserCreateInnerRequest.java, v 0.1 2022‐12‐26 7:18 AM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class UserCreateInnerRequest extends BaseInnerRequest {
    private String userId;
    private String username;
    private String email;
    private String phoneNumber;
    private String profilePicture;
    private String roleId;
    private String password;
}
