/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: FunctionUtil.java, v 0.1 2022‐12‐26 7:51 AM Aldih Suhandi Exp $$
 */
public class FunctionUtil {

    private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    public static String encryptPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }
}
