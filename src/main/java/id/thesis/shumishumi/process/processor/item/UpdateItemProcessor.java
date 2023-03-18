package id.thesis.shumishumi.process.processor.item;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.enumeration.UserRolesEnum;
import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.service.ItemImageService;
import id.thesis.shumishumi.core.service.ItemService;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.process.processor.BaseProcessor;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.item.UpdateItemRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;

public class UpdateItemProcessor implements BaseProcessor {


    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemImageService itemImageService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        UpdateItemRequest updateRequest = (UpdateItemRequest) baseRequest;

        ItemVO itemVO = itemService.queryById(updateRequest.getItemId(), true);
        SessionVO sessionVO = sessionService.query(updateRequest.getSessionId());
        UserVO userVO = userService.queryById(sessionVO.getUserId(), true);

        AssertUtil.isNotNull(itemVO, "item not found", ShumishumiErrorCodeEnum.ITEM_NOT_FOUND);
        AssertUtil.isExpected(UserRolesEnum.MERCHANT.getUserRoleName(), userVO.getRoleVO().getRoleName(),
                "user is not a merchant", ShumishumiErrorCodeEnum.USER_ROLE_INVALID);
        AssertUtil.isExpected(userVO.getUserId(), itemVO.getMerchantInfo().getUserId(),
                "this item is not from this user", ShumishumiErrorCodeEnum.USER_ROLE_INVALID);

        itemService.update(itemVO, updateRequest.getItemUpdateContext());
        itemImageService.create(updateRequest.getItemUpdateContext().getAddedItemImages(), itemVO.getItemId());
        itemImageService.delete(updateRequest.getItemUpdateContext().getRemovedItemImages());

        itemService.refreshCache(new ArrayList<>(Collections.singletonList(itemVO.getItemId())), false);
    }
}
