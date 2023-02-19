/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.facade.request.client;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ClientAuthRequest.java, v 0.1 2022‐12‐26 3:24 PM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class ClientAuthRequest extends BaseRequest {
    private String clientId;
    private String clientSecret;
}
