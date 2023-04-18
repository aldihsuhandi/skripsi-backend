/**
 * Dana.id
 * Copyright (c) 2017-2023 All Rights Reserved.
 */
package id.thesis.shumishumi.foundation.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;

/**
 * @author Aldih Suhandi <i-aldih.suhandi@dana.id>
 * @version $Id: PostVoteDO.java, v 0.1 2023‐04‐11 15.39 Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class PostVoteDO extends BaseDO {
    private String userId;
    private String postId;
    private int value;
}
