/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.util;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.context.ItemFilterContext;
import id.thesis.shumishumi.common.model.context.ItemUpdateContext;
import id.thesis.shumishumi.common.model.context.UserUpdateContext;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.common.model.viewobject.PostVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.core.request.post.PostEditRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.util.UUID;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
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
        updateContext.setProfilePicture(checkIfNotEmpty(updateContext.getProfilePicture()) ?
                updateContext.getProfilePicture() : userVO.getProfilePicture());
        updateContext.setIsDeleted(updateContext.getIsDeleted() != null ?
                updateContext.getIsDeleted() : userVO.isDeleted());
        updateContext.setIsActive(updateContext.getIsActive() != null ?
                updateContext.getIsActive() : userVO.isActive());
    }

    public static void fillEmptyUpdateContext(ItemUpdateContext updateContext, ItemVO itemVO) {
        updateContext.setItemName(checkIfNotEmpty(updateContext.getItemName()) ?
                updateContext.getItemName() : itemVO.getItemName());
        updateContext.setItemDescription(checkIfNotEmpty(updateContext.getItemDescription()) ?
                updateContext.getItemDescription() : itemVO.getItemDescription());
        updateContext.setItemPrice(updateContext.getItemPrice() != null ? updateContext.getItemPrice() :
                itemVO.getItemPrice());
        updateContext.setItemQuantity(updateContext.getItemQuantity() != null ? updateContext.getItemQuantity() :
                itemVO.getItemQuantity());
        updateContext.setHobbyName(checkIfNotEmpty(updateContext.getHobbyName()) ?
                updateContext.getHobbyName() : itemVO.getHobby().getHobbyName());
        updateContext.setCategoryName(checkIfNotEmpty(updateContext.getCategoryName()) ?
                updateContext.getHobbyName() : itemVO.getItemCategory().getCategoryName());
        updateContext.setMerchantInterestLevel(checkIfNotEmpty(updateContext.getMerchantInterestLevel()) ?
                updateContext.getMerchantInterestLevel() : itemVO.getMerchantLevel().getInterestLevelName());
    }

    public static void updatePostField(PostVO postVO, PostEditRequest request) {
        if (postVO == null || request == null) {
            return;
        }
        if (!CollectionUtils.isEmpty(request.getTags())) {
            postVO.setTags(request.getTags());
        }
        if (!CollectionUtils.isEmpty(request.getImages())) {
            postVO.setTags(request.getImages());
        }
        if (StringUtils.isNotEmpty(request.getTitle())) {
            postVO.setTitle(request.getTitle());
        }
        if (StringUtils.isNotEmpty(request.getContent())) {
            postVO.setContent(request.getContent());
        }
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

        if (checkIfNotEmpty(filterContext.getMerchantInterestLevel())) {
            result = result && filterContext.getMerchantInterestLevel().
                    equalsIgnoreCase(itemVO.getMerchantLevel().getInterestLevelName());
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

        result = result && (filterContext.isApproved() == itemVO.isApproved());
        result = result && (filterContext.isDeleted() == itemVO.isDeleted());

        return result;
    }

    public static String hideString(String field) {
        if (field == null || field.isEmpty()) {
            return "";
        }
        return "*".repeat(field.length());
    }

    private static boolean checkIfNotEmpty(String s) {
        return s != null && !s.isEmpty();
    }

    public static boolean verifyHash(String password, String hash) {
        return bCryptPasswordEncoder.matches(password, hash);
    }

    public static Blob convertToBlob(MultipartFile multipartFile) {
        if (multipartFile == null) {
            return null;
        }

        try {
            return new SerialBlob(multipartFile.getBytes());
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }
}
