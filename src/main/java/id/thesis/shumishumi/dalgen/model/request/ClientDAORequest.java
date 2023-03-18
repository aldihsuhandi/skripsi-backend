/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.dalgen.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ClientDAORequest.java, v 0.1 2022‐12‐26 3:31 PM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class ClientDAORequest implements Serializable {
    private static final long serialVersionUID = -7069322351489099002L;

    private String clientId;
    private String clientName;
    private String clientSecret;
}
