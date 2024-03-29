/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.facade.request.session;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: SessionQueryRequest.java, v 0.1 2022‐12‐30 1:12 PM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class SessionQueryRequest extends BaseRequest {
    private String sessionId;
}
