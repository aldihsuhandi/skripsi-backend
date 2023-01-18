/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.dalgen.model.result;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: HobbyDO.java, v 0.1 2023‐01‐17 13:09 Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class HobbyDO extends BaseDO{
    private static final long serialVersionUID = -147906779250897196L;

    private String hobbyId;
    private String hobbyName;
}