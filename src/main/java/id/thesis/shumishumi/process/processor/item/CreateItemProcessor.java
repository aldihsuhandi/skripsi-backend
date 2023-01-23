/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.process.processor.item;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.converter.ItemRequestConverter;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.enumeration.UserRolesEnum;
import id.thesis.shumishumi.common.model.request.item.CreateItemInnerRequest;
import id.thesis.shumishumi.common.model.viewobject.HobbyVO;
import id.thesis.shumishumi.common.model.viewobject.InterestLevelVO;
import id.thesis.shumishumi.common.model.viewobject.ItemCategoryVO;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.service.HobbyService;
import id.thesis.shumishumi.core.service.InterestLevelService;
import id.thesis.shumishumi.core.service.ItemCategoryService;
import id.thesis.shumishumi.core.service.ItemImageService;
import id.thesis.shumishumi.core.service.ItemService;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.process.processor.BaseProcessor;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.item.CreateItemRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: CreateItemProcessor.java, v 0.1 2023‐01‐16 4:27 PM Aldih Suhandi Exp $$
 */
public class CreateItemProcessor implements BaseProcessor {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemImageService itemImageService;

    @Autowired
    private ItemCategoryService itemCategoryService;

    @Autowired
    private HobbyService hobbyService;

    @Autowired
    private InterestLevelService interestLevelService;

    @Override
    public void doProcess(BaseResult result, BaseRequest request) {
        CreateItemRequest itemRequest = (CreateItemRequest) request;

        SessionVO sessionVO = sessionService.query(itemRequest.getSessionId());
        AssertUtil.isNotNull(sessionVO, "session not exist", ShumishumiErrorCodeEnum.SESSION_EXPIRED);

        UserVO merchant = userService.queryById(sessionVO.getUserId(), true);
        AssertUtil.isNotNull(merchant, "user not exist", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(merchant.isActive(), "user not active", ShumishumiErrorCodeEnum.USER_NOT_ACTIVE);
        AssertUtil.isExpected(!merchant.isDeleted(), "user not exist", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(UserRolesEnum.MERCHANT.getUserRoleName().equals(merchant.getRoleVO().getRoleName()),
                "user is not a merchant", ShumishumiErrorCodeEnum.USER_ROLE_INVALID);

        ItemCategoryVO itemCategoryVO = itemCategoryService.
                query(itemRequest.getCategoryName(), DatabaseConst.CATEGORY_NAME);

        HobbyVO hobbyVO = hobbyService.
                query(itemRequest.getHobbyName(), DatabaseConst.HOBBY_NAME);

        InterestLevelVO interestLevelVO = interestLevelService.
                query(itemRequest.getMerchantInterestLevel(), DatabaseConst.INTEREST_LEVEL_NAME);

        AssertUtil.isNotNull(itemCategoryVO, "item category not found", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        AssertUtil.isNotNull(hobbyVO, "hobby not found", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        AssertUtil.isNotNull(interestLevelVO, "merchant interest level not found", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        CreateItemInnerRequest innerRequest = ItemRequestConverter.toInnerRequest(itemRequest, merchant.getUserId(),
                itemCategoryVO.getCategoryId(), hobbyVO.getHobbyId(), interestLevelVO.getInterestLevelId());

        itemService.create(innerRequest);
        itemImageService.create(itemRequest.getItemImages(), innerRequest.getItemId());
        itemService.refreshCache(new ArrayList<>(Collections.singletonList(innerRequest.getItemId())), false);
    }
}
