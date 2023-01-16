/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.process.processor.item;

import id.thesis.shumishumi.common.converter.ItemRequestConverter;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.enumeration.UserRolesEnum;
import id.thesis.shumishumi.common.model.request.item.CreateItemInnerRequest;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.service.ItemService;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.process.processor.BaseProcessor;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.item.CreateItemRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

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

    @Override
    public void doProcess(BaseResult result, BaseRequest request) {
        CreateItemRequest itemRequest = (CreateItemRequest) request;

        SessionVO sessionVO = sessionService.query(itemRequest.getSessionId());
        AssertUtil.isNotNull(sessionVO, "session not exist", ShumishumiErrorCodeEnum.SESSION_EXPIRED);

        UserVO userVO = userService.queryById(sessionVO.getUserId(), true);
        AssertUtil.isNotNull(userVO, "user not exist", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(userVO.isActive(), "user not active", ShumishumiErrorCodeEnum.USER_NOT_ACTIVE);
        AssertUtil.isExpected(!userVO.isDeleted(), "user not exist", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(UserRolesEnum.MERCHANT.getUserRoleName().
                equals(userVO.getRoleVO().getRoleName()), "user is not a merchant", ShumishumiErrorCodeEnum.USER_ROLE_INVALID);

        CreateItemInnerRequest innerRequest = ItemRequestConverter.toInnerRequest(itemRequest, userVO.getUserId());
        itemService.create(innerRequest);
    }
}
