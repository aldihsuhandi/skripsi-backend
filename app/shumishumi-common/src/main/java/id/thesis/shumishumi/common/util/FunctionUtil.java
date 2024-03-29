/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.util;

import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.context.ItemFilterContext;
import id.thesis.shumishumi.facade.model.context.ItemUpdateContext;
import id.thesis.shumishumi.facade.model.context.UserUpdateContext;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.model.viewobject.PostVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.facade.request.post.PostEditRequest;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: FunctionUtil.java, v 0.1 2022‐12‐26 7:51 AM Aldih Suhandi Exp $$
 */
public class FunctionUtil {

    private static final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String hashPassword(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public static String generateString(int length, boolean useNumber, boolean useLetter) {
        return RandomStringUtils.random(length, useLetter, useNumber);
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
        updateContext.setRoleId(checkIfNotEmpty(updateContext.getRoleId()) ?
                updateContext.getRoleId() : userVO.getRoleVO().getRoleId());
        updateContext.setIsDeleted(updateContext.getIsDeleted() != null ?
                updateContext.getIsDeleted() : userVO.isDeleted());
        updateContext.setIsActive(updateContext.getIsActive() != null ?
                updateContext.getIsActive() : userVO.isActive());
        updateContext.setDateOfBirth(updateContext.getDateOfBirth() != null ?
                updateContext.getDateOfBirth() : userVO.getDateOfBirth());
        updateContext.setGender(checkIfNotEmpty(updateContext.getGender()) ?
                updateContext.getGender() : userVO.getGender());
        updateContext.setLocation(fillEmptyLocation(userVO.getLocation(), updateContext.getLocation()));
        updateContext.setExtendInfo(CollectionUtils.isEmpty(updateContext.getExtendInfo()) ?
                userVO.getExtendInfo() : updateContext.getExtendInfo());
    }

    private static Map<String, String> fillEmptyLocation(Map<String, String> currentLocation, Map<String, String> newLocation) {
        if (CollectionUtils.isEmpty(newLocation)) {
            return currentLocation;
        }

        Map<String, String> location = new HashMap<>();
        List<String> locationConst = new ArrayList<>();
        locationConst.add(CommonConst.LOCATION_CITY);
        locationConst.add(CommonConst.LOCATION_PROVINCE);
        locationConst.add(CommonConst.LOCATION_POST_CODE);
        locationConst.add(CommonConst.LOCATION_DETAIL);

        locationConst.forEach(locConst -> {
            if (newLocation.containsKey(locConst) &&
                    StringUtils.isNotEmpty(newLocation.get(locConst))) {
                location.put(locConst, newLocation.get(locConst));
            } else {
                location.put(locConst, currentLocation.
                        getOrDefault(locConst, StringUtils.EMPTY));
            }
        });

        return location;
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
                updateContext.getCategoryName() : itemVO.getItemCategory().getCategoryName());
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
            result = itemVO.getItemName().toLowerCase().contains(filterContext.getItemName().toLowerCase());
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

    private static boolean checkIfNotEmpty(String s) {
        return s != null && !s.isEmpty();
    }

    public static boolean verifyHash(String password, String hash) {
        return bCryptPasswordEncoder.matches(password, hash);
    }

    public static double roundDouble(double n) {
        DecimalFormat format = new DecimalFormat("#.#");
        String roundStr = format.format(n);

        return Double.parseDouble(roundStr);
    }

}
