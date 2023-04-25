/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.converter;

import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.enumeration.OTPTypeEnum;
import id.thesis.shumishumi.facade.model.viewobject.ActivityVO;
import id.thesis.shumishumi.facade.model.viewobject.ClientVO;
import id.thesis.shumishumi.facade.model.viewobject.HobbyVO;
import id.thesis.shumishumi.facade.model.viewobject.ImageVO;
import id.thesis.shumishumi.facade.model.viewobject.InterestLevelVO;
import id.thesis.shumishumi.facade.model.viewobject.ItemCategoryVO;
import id.thesis.shumishumi.facade.model.viewobject.ItemImageVO;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.model.viewobject.ItemWishlistVO;
import id.thesis.shumishumi.facade.model.viewobject.OtpVO;
import id.thesis.shumishumi.facade.model.viewobject.PostVO;
import id.thesis.shumishumi.facade.model.viewobject.RoleVO;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.model.viewobject.UserActivityVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.foundation.model.result.ActivityDO;
import id.thesis.shumishumi.foundation.model.result.ClientDO;
import id.thesis.shumishumi.foundation.model.result.HobbyDO;
import id.thesis.shumishumi.foundation.model.result.ImageDO;
import id.thesis.shumishumi.foundation.model.result.InterestLevelDO;
import id.thesis.shumishumi.foundation.model.result.ItemCategoryDO;
import id.thesis.shumishumi.foundation.model.result.ItemDO;
import id.thesis.shumishumi.foundation.model.result.ItemImageDO;
import id.thesis.shumishumi.foundation.model.result.ItemWishlistDO;
import id.thesis.shumishumi.foundation.model.result.OtpDO;
import id.thesis.shumishumi.foundation.model.result.PostDO;
import id.thesis.shumishumi.foundation.model.result.RoleDO;
import id.thesis.shumishumi.foundation.model.result.SessionDO;
import id.thesis.shumishumi.foundation.model.result.UserActivityDO;
import id.thesis.shumishumi.foundation.model.result.UserDO;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        itemVO.setGmtCreate(itemDO.getGmtCreate());
        itemVO.setGmtModified(itemDO.getGmtModified());

        if (StringUtils.isNotEmpty(itemDO.getItemImages())) {
            itemVO.setItemImages(Arrays.asList(itemDO.getItemImages().split(CommonConst.SEPARATOR_SPLIT)));
        }

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

    public static ItemWishlistVO toViewObject(ItemWishlistDO wishlistDo) {
        if (wishlistDo == null) {
            return null;
        }

        ItemWishlistVO vo = new ItemWishlistVO();
        vo.setUserId(wishlistDo.getPk().getUserId());
        vo.setItemId(wishlistDo.getPk().getItemId());
        vo.setGmtCreate(wishlistDo.getGmtCreate());
        vo.setGmtModified(wishlistDo.getGmtModified());

        return vo;
    }

    public static ImageVO toViewObject(ImageDO imageDO) {
        if (imageDO == null) {
            return null;
        }
        ImageVO imageVO = new ImageVO();
        imageVO.setImageId(imageDO.getImageId());
        imageVO.setImageExt(imageDO.getImageExt());
        imageVO.setImageName(imageDO.getImageName());
        imageVO.setImage(imageDO.getImage());
        imageVO.setGmtCreate(imageDO.getGmtCreate());
        imageVO.setGmtModified(imageDO.getGmtModified());

        return imageVO;
    }

    public static PostVO toViewObject(PostDO postDO) {
        if (postDO == null) {
            return null;
        }

        List<String> tags = new ArrayList<>();
        List<String> images = new ArrayList<>();

        if (!postDO.getImages().isEmpty()) {
            images.addAll(List.of(postDO.getImages().split(CommonConst.SEPARATOR_SPLIT)));
        }
        if (!postDO.getTags().isEmpty()) {
            tags.addAll(Arrays.asList(postDO.getTags().split(CommonConst.SEPARATOR_SPLIT)));
        }

        PostVO vo = new PostVO();
        vo.setPostId(postDO.getPostId());
        vo.setUserId(postDO.getUserId());
        vo.setTitle(postDO.getTitle());
        vo.setContent(postDO.getContent());
        vo.setImages(images);
        vo.setTags(tags);
        vo.setGmtCreate(postDO.getGmtCreate());
        vo.setGmtModified(postDO.getGmtModified());

        return vo;
    }
}
