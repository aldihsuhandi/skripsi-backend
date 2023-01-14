/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.rest.request.session;

import id.thesis.shumishumi.rest.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: SessionLogoutRequest.java, v 0.1 2022‐12‐27 7:42 AM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class SessionLogoutRequest extends BaseRequest {
    private String sessionId;
}