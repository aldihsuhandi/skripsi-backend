/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.model.request.user;

import id.thesis.shumishumi.common.model.context.UserUpdateContext;
import id.thesis.shumishumi.common.model.request.BaseInnerRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserUpdateInnerRequest.java, v 0.1 2022‐12‐26 8:23 AM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class UserUpdateInnerRequest extends BaseInnerRequest {
    private String userId;
    private UserUpdateContext userUpdateContext;
}
