/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.model.context;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: Headers.java, v 0.1 2022‐12‐26 2:59 PM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class Headers {
    private String clientId;
    private String clientSecret;
    private String sessionId;
}
