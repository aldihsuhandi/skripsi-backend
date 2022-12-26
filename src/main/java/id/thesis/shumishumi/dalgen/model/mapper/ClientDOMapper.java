/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.dalgen.model.mapper;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.dalgen.model.result.ClientDO;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ClientDOMapper.java, v 0.1 2022‐12‐26 3:31 PM Aldih Suhandi Exp $$
 */
public class ClientDOMapper implements RowMapper<ClientDO> {
    @Override
    public ClientDO mapRow(ResultSet rs, int rowNum) throws SQLException {
        ClientDO clientDO = new ClientDO();
        clientDO.setClientId(rs.getString(DatabaseConst.CLIENT_ID));
        clientDO.setClientName(rs.getString(DatabaseConst.CLIENT_NAME));
        clientDO.setClientSecret(rs.getString(DatabaseConst.CLIENT_SECRET));

        return clientDO;
    }
}
