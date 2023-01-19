/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.dalgen.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: UserActivityDAORequest.java, v 0.1 2023‐01‐19 9:29 AM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class UserActivityDAORequest implements Serializable {
    private static final long serialVersionUID = 2655079791062927912L;

    private String userActivityId;
    private String userId;
    private String itemId;
    private String activitytId;
    private Date gmtCreate;
    private Date gmtModified;
}
