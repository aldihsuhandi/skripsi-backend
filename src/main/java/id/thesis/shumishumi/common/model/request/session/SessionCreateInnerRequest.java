/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.model.request.session;

import id.thesis.shumishumi.common.model.request.BaseInnerRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: SessionCreateInnerRequest.java, v 0.1 2022‐12‐27 7:21 AM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class SessionCreateInnerRequest extends BaseInnerRequest {
    private String sessionId;
    private String userId;
    private boolean isRemembered;
    private Date sessionDt;
}
