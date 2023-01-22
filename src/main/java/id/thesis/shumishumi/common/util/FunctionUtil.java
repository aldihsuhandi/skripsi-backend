/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.util;

import id.thesis.shumishumi.common.model.context.ItemFilterContext;
import id.thesis.shumishumi.common.model.context.ItemUpdateContext;
import id.thesis.shumishumi.common.model.context.UserUpdateContext;
import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.UUID;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: FunctionUtil.java, v 0.1 2022‐12‐26 7:51 AM Aldih Suhandi Exp $$
 */
public class FunctionUtil {

    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

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

    public static void fillEmptyUpdateContext(ItemUpdateContext updateContext, ItemVO itemVO) {
    }

    public static boolean itemFilter(ItemVO itemVO, ItemFilterContext filterContext) {
        boolean result = true;
        if (checkIfNotEmpty(filterContext.getItemName())) {
            result = itemVO.getItemName().contains(filterContext.getItemName());
        }

        if (checkIfNotEmpty(filterContext.getItemCategory())) {
            result = result && filterContext.getItemCategory().
                    equalsIgnoreCase(itemVO.getItemCategory().getCategoryName());
        }

        if (checkIfNotEmpty(filterContext.getHobby())) {
            result = result && filterContext.getHobby().
                    equalsIgnoreCase(itemVO.getHobby().getHobbyName());
        }

        if (checkIfNotEmpty(filterContext.getUserInterestLevel())) {
            result = result && filterContext.getUserInterestLevel().
                    equalsIgnoreCase(itemVO.getUserLevel().getInterestLevelName());
        }

        if (checkIfNotEmpty(filterContext.getMerchantInterestLevel())) {
            result = result && filterContext.getMerchantInterestLevel().
                    equalsIgnoreCase(itemVO.getUserLevel().getInterestLevelName());
        }

        if (checkIfNotEmpty(filterContext.getMerchantId())) {
            result = result && filterContext.getMerchantId().
                    equalsIgnoreCase(itemVO.getMerchantInfo().getUserId());
        }

        if (filterContext.getMinItemPrice() != null) {
            result = result && (filterContext.getMinItemPrice() <= itemVO.getItemPrice());
        }

        if (filterContext.getMaxItemPrice() != null) {
            result = result && (filterContext.getMaxItemPrice() >= itemVO.getItemPrice());
        }

        return result;
    }

    public static String hideString(String field) {
        return "*".repeat(field.length());
    }

    private static boolean checkIfNotEmpty(String s) {
        return s != null && !s.isEmpty();
    }

    public static boolean verifyHash(String password, String hash) {
        return bCryptPasswordEncoder.matches(password, hash);
    }
}
