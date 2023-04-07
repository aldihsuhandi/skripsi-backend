/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.request.user;

import id.thesis.shumishumi.core.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserQueryRequest.java, v 0.1 2022‐12‐28 9:16 AM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class UserQueryRequest extends BaseRequest {
    private String key;
    private String identifier;
}
