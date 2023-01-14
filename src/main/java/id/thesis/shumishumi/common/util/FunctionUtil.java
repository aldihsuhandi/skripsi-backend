/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.util;

import id.thesis.shumishumi.common.model.context.UserUpdateContext;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import org.apache.commons.lang3.RandomStringUtils;
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

    public static String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public static String generateOtp(int length, boolean useNumber, boolean useLetter) {
        return RandomStringUtils.random(length, useNumber, useLetter);
    }

    public static void fillEmptyUpdateContext(UserUpdateContext updateContext, UserVO userVO) {
        updateContext.setEmail(checkIfNotEmpty(updateContext.getEmail()) ? updateContext.getEmail() : userVO.getEmail());
        updateContext.setPassword(checkIfNotEmpty(updateContext.getPassword()) ?
                FunctionUtil.hashPassword(updateContext.getPassword()) : userVO.getPassword());
        updateContext.setUsername(checkIfNotEmpty(updateContext.getUsername()) ?
                updateContext.getUsername() : userVO.getUsername());
        updateContext.setPhoneNumber(checkIfNotEmpty(updateContext.getPhoneNumber()) ?
                updateContext.getPhoneNumber() : userVO.getPhoneNumber());
        updateContext.setProfilePicture(updateContext.getProfilePicture() != null ?
                updateContext.getProfilePicture() : userVO.getProfilePicture());
        updateContext.setIsDeleted(updateContext.getIsDeleted() != null ?
                updateContext.getIsDeleted() : userVO.isDeleted());
        updateContext.setIsActive(updateContext.getIsActive() != null ?
                updateContext.getIsActive() : userVO.isActive());
    }


    private static boolean checkIfNotEmpty(String s) {
        return s != null && !s.isEmpty();
    }

    public static boolean verifyHash(String password, String hash) {
        return bCryptPasswordEncoder.matches(password, hash);
    }
}