/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.model.enumeration;

import lombok.Getter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: UserRolesEnum.java, v 0.1 2022‐12‐26 7:40 AM Aldih Suhandi Exp $$
 */
@Getter
@ToString
public enum UserRolesEnum {
    USER("8e6993f7-356f-4a77-9cbb-bffbde74f5fc", "USER"),
    MERCHANT("5e462578-658c-4e40-8f06-4125f8aa413e", "MERCHANT"),
    ADMIN("ccde9d0c-eb08-482b-b2ee-76fcf2f47ef7", "ADMIN");

    UserRolesEnum(String userRoleId, String userRoleName) {
        this.userRoleId = userRoleId;
        this.userRoleName = userRoleName;
    }

    private String userRoleId;
    private String userRoleName;
}
