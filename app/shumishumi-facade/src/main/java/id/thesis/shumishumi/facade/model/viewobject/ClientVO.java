/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.facade.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ClientVO.java, v 0.1 2022‐12‐26 3:30 PM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class ClientVO extends BaseVO {
    String clientId;
    String clientName;
    String clientSecret;
}
