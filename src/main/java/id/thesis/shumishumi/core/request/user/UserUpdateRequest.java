/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.request.user;

import id.thesis.shumishumi.common.model.context.UserUpdateContext;
import id.thesis.shumishumi.core.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserUpdateRequest.java, v 0.1 2022‐12‐27 1:43 PM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class UserUpdateRequest extends BaseRequest {
    private String password;
    private UserUpdateContext userUpdateContext;
}
