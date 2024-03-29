/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.foundation.converter;


import id.thesis.shumishumi.foundation.model.request.ClientDAORequest;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ClientDAORequestConverter.java, v 0.1 2022‐12‐26 3:38 PM Aldih Suhandi Exp $$
 */
public class ClientDAORequestConverter {
    public static ClientDAORequest toDAORequest(String clientId) {
        ClientDAORequest daoRequest = new ClientDAORequest();
        daoRequest.setClientId(clientId);

        return daoRequest;
    }
}
