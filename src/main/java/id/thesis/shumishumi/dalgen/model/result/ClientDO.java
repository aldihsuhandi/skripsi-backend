/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ClientDO.java, v 0.1 2022‐12‐26 3:32 PM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class ClientDO extends BaseDO {
    String clientId;
    String clientName;
    String clientSecret;
}
