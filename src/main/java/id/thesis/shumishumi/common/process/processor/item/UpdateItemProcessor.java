package id.thesis.shumishumi.common.process.processor.item;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.enumeration.UserRolesEnum;
import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.process.processor.BaseProcessor;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.item.UpdateItemRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.service.ItemService;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class UpdateItemProcessor implements BaseProcessor {


    @Autowired
    private ItemService itemService;

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

        this.updateImage(itemVO, updateRequest.getItemUpdateContext().getAddedImage(),
                updateRequest.getItemUpdateContext().getRemovedImage());
        itemService.update(itemVO, updateRequest.getItemUpdateContext());

        itemService.refreshCache(new ArrayList<>(Collections.singletonList(itemVO.getItemId())), false);
    }

    private void updateImage(ItemVO itemVO, List<String> addedImage, List<String> removedImage) {
        List<String> currImage = itemVO.getItemImages();
        List<String> images = new ArrayList<>(addedImage);

        for (String image : currImage) {
            if (!removedImage.contains(image)) {
                images.add(image);
            }
        }

        AssertUtil.isExpected(!images.isEmpty(), "Item Image cannot be empty", ShumishumiErrorCodeEnum.SYSTEM_ERROR);

        itemService.updatePicture(itemVO.getItemId(), images);
    }
}
