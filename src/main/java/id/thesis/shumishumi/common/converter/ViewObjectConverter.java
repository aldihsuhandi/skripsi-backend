/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.converter;

import id.thesis.shumishumi.common.model.enumeration.OTPTypeEnum;
import id.thesis.shumishumi.common.model.viewobject.ActivityVO;
import id.thesis.shumishumi.common.model.viewobject.ClientVO;
import id.thesis.shumishumi.common.model.viewobject.HobbyVO;
import id.thesis.shumishumi.common.model.viewobject.InterestLevelVO;
import id.thesis.shumishumi.common.model.viewobject.ItemCategoryVO;
import id.thesis.shumishumi.common.model.viewobject.ItemImageVO;
import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.common.model.viewobject.OtpVO;
import id.thesis.shumishumi.common.model.viewobject.RoleVO;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.common.model.viewobject.UserActivityVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.foundation.dalgen.model.result.ActivityDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.ClientDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.HobbyDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.InterestLevelDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.ItemCategoryDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.ItemDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.ItemImageDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.OtpDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.RoleDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.SessionDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.UserActivityDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.UserDO;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ViewObjectConverter.java, v 0.1 2022‐12‐26 2:31 PM Aldih Suhandi Exp $$
 */
public class ViewObjectConverter {
    public static UserVO toViewObject(UserDO userDO) {
        if (userDO == null) {
            return null;
        }

        UserVO userVO = new UserVO();
        userVO.setUserId(userDO.getUserId());
        userVO.setPassword(userDO.getPassword());
        userVO.setUsername(userDO.getUsername());
        userVO.setEmail(userDO.getEmail());
        userVO.setPhoneNumber(userDO.getPhoneNumber());
        userVO.setProfilePicture(userDO.getProfilePicture());
        userVO.setDeleted(userDO.isDeleted());
        userVO.setActive(userDO.isActive());
        userVO.setGmtCreate(userDO.getGmtCreate());
        userVO.setGmtModified(userDO.getGmtModified());

        return userVO;
    }

    public static ClientVO toViewObject(ClientDO clientDO) {
        if (clientDO == null) {
            return null;
        }

        ClientVO clientVO = new ClientVO();
        clientVO.setClientName(clientDO.getClientName());
        clientVO.setClientSecret(clientDO.getClientSecret());
        clientVO.setClientId(clientDO.getClientId());
        clientVO.setGmtCreate(clientDO.getGmtCreate());
        clientVO.setGmtModified(clientDO.getGmtModified());

        return clientVO;
    }

    public static SessionVO toViewObject(SessionDO sessionDO) {
        if (sessionDO == null) {
            return null;
        }

        SessionVO sessionVO = new SessionVO();
        sessionVO.setSessionId(sessionDO.getSessionId());
        sessionVO.setUserId(sessionDO.getUserId());
        sessionVO.setSessionDt(sessionDO.getSessionDt());
        sessionVO.setRemembered(sessionDO.isRemembered());
        sessionVO.setActive(sessionDO.isActive());
        sessionVO.setGmtCreate(sessionDO.getGmtCreate());
        sessionVO.setGmtModified(sessionDO.getGmtModified());

        return sessionVO;

    }

    public static RoleVO toViewObject(RoleDO roleDO) {
        if (roleDO == null) {
            return null;
        }
        RoleVO roleVO = new RoleVO();
        roleVO.setRoleId(roleDO.getRoleId());
        roleVO.setRoleName(roleDO.getRoleName());
        roleVO.setGmtCreate(roleDO.getGmtCreate());
        roleVO.setGmtModified(roleDO.getGmtModified());

        return roleVO;
    }

    public static OtpVO toViewObject(OtpDO otpDO) {
        if (otpDO == null) {
            return null;
        }

        OtpVO otpVO = new OtpVO();
        otpVO.setOtpId(otpDO.getOtpId());
        otpVO.setOtpDt(otpDO.getOtpDt());
        otpVO.setOtp(otpDO.getOtp());
        otpVO.setOtpType(OTPTypeEnum.findById(otpDO.getTypeId()).getName());
        otpVO.setActive(otpDO.isActive());
        otpVO.setEmail(otpDO.getEmail());
        otpVO.setGmtCreate(otpDO.getGmtCreate());
        otpVO.setGmtModified(otpDO.getGmtModified());

        return otpVO;
    }

    public static InterestLevelVO toViewObject(InterestLevelDO interestLevelDO) {
        if (interestLevelDO == null) {
            return null;
        }

        InterestLevelVO interestLevelVO = new InterestLevelVO();
        interestLevelVO.setInterestLevelId(interestLevelDO.getInterestLevelId());
        interestLevelVO.setInterestLevelName(interestLevelDO.getInterestLevelName());
        interestLevelVO.setGmtCreate(interestLevelDO.getGmtCreate());
        interestLevelVO.setGmtModified(interestLevelDO.getGmtModified());

        return interestLevelVO;
    }

    public static HobbyVO toViewObject(HobbyDO hobbyDO) {
        if (hobbyDO == null) {
            return null;
        }

        HobbyVO hobbyVO = new HobbyVO();
        hobbyVO.setHobbyName(hobbyDO.getHobbyName());
        hobbyVO.setHobbyId(hobbyDO.getHobbyId());
        hobbyVO.setGmtCreate(hobbyDO.getGmtCreate());
        hobbyVO.setGmtModified(hobbyDO.getGmtModified());

        return hobbyVO;
    }

    public static ItemCategoryVO toViewObject(ItemCategoryDO itemCategoryDO) {
        if (itemCategoryDO == null) {
            return null;
        }

        ItemCategoryVO itemCategoryVO = new ItemCategoryVO();
        itemCategoryVO.setCategoryId(itemCategoryDO.getCategoryId());
        itemCategoryVO.setCategoryName(itemCategoryDO.getCategoryName());
        itemCategoryVO.setGmtCreate(itemCategoryDO.getGmtCreate());
        itemCategoryVO.setGmtModified(itemCategoryDO.getGmtModified());

        return itemCategoryVO;
    }

    public static ItemVO toViewObject(ItemDO itemDO) {
        if (itemDO == null) {
            return null;
        }

        UserVO merchantInfo = new UserVO();
        HobbyVO hobbyVO = new HobbyVO();
        ItemCategoryVO itemCategoryVO = new ItemCategoryVO();
        InterestLevelVO merchantLevel = new InterestLevelVO();
        InterestLevelVO userLevel = new InterestLevelVO();

        merchantInfo.setUserId(itemDO.getMerchantId());
        hobbyVO.setHobbyId(itemDO.getHobbyId());
        itemCategoryVO.setCategoryId(itemDO.getCategoryId());
        merchantLevel.setInterestLevelId(itemDO.getMerchantLevelId());

        ItemVO itemVO = new ItemVO();
        itemVO.setItemId(itemDO.getItemId());
        itemVO.setItemName(itemDO.getItemName());
        itemVO.setItemPrice(itemDO.getItemPrice());
        itemVO.setItemDescription(itemDO.getItemDescription());
        itemVO.setItemQuantity(itemDO.getItemQuantity());
        itemVO.setItemCategory(itemCategoryVO);
        itemVO.setHobby(hobbyVO);
        itemVO.setMerchantInfo(merchantInfo);
        itemVO.setMerchantLevel(merchantLevel);
        itemVO.setDeleted(itemDO.isDeleted());
        itemVO.setApproved(itemDO.isApproved());

        return itemVO;
    }

    public static ItemImageVO toViewObject(ItemImageDO imageDO) {
        if (imageDO == null) {
            return null;
        }

        ItemImageVO imageVO = new ItemImageVO();
        imageVO.setItemImageId(imageVO.getItemImageId());
        imageVO.setItemImage(imageDO.getItemImage());
        imageVO.setItemId(imageDO.getItemId());
        imageVO.setDeleted(imageDO.isDeleted());
        imageVO.setGmtCreate(imageDO.getGmtCreate());
        imageVO.setGmtModified(imageDO.getGmtModified());

        return imageVO;
    }

    public static UserActivityVO toViewObject(UserActivityDO userActivityDO) {
        if (userActivityDO == null) {
            return null;
        }

        UserActivityVO userActivityVO = new UserActivityVO();
        ActivityVO activityVO = new ActivityVO();

        activityVO.setActivityId(userActivityVO.getUserActivityId());

        userActivityVO.setItemId(userActivityDO.getItemId());
        userActivityVO.setUserActivityId(userActivityDO.getUserActivityId());
        userActivityVO.setUserId(userActivityDO.getUserId());
        userActivityVO.setActivityInfo(activityVO);
        userActivityVO.setGmtCreate(userActivityDO.getGmtCreate());
        userActivityVO.setGmtModified(userActivityDO.getGmtModified());

        return userActivityVO;
    }

    public static ActivityVO toViewObject(ActivityDO activityDO) {
        if (activityDO == null) {
            return null;
        }

        ActivityVO activityVO = new ActivityVO();
        activityVO.setActivityId(activityVO.getActivityName());
        activityVO.setActivityName(activityDO.getActivityName());
        activityVO.setPoint(activityDO.getPoint());
        activityVO.setGmtCreate(activityDO.getGmtCreate());
        activityVO.setGmtModified(activityDO.getGmtModified());

        return activityVO;
    }
}
