/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ClientVO.java, v 0.1 2022‐12‐26 3:30 PM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class ClientVO {
    String clientId;
    String clientName;
    String clientSecret;
}
