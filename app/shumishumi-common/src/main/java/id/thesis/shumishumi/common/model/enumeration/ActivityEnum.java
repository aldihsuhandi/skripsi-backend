/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.common.model.enumeration;

import lombok.Getter;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: ActivityEnum.java, v 0.1 2023‐01‐19 10:56 AM Aldih Suhandi Exp $$
 */
@Getter
public enum ActivityEnum {

    BUY("BUY"),
    LOOK("LOOK"),
    ;

    private String name;

    ActivityEnum(String name) {
        this.name = name;
    }
}
