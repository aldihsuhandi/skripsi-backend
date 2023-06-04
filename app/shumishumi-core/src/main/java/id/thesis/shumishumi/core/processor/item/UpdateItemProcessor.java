package id.thesis.shumishumi.core.processor.item;

import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.KnowledgeService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.enumeration.UserRolesEnum;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.item.UpdateItemRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

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

    @Autowired
    private KnowledgeService knowledgeService;

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

        knowledgeService.removeItemFromKnowledge(itemVO);

        itemService.update(itemVO, updateRequest.getItemUpdateContext(), updateImage(
                itemVO.getItemImages(), updateRequest.getItemUpdateContext().getAddedImage(),
                updateRequest.getItemUpdateContext().getRemovedImage()));

        itemVO = itemService.queryById(updateRequest.getItemId(), false);
        knowledgeService.addItemToKnowledge(itemVO);
    }

    private List<String> updateImage(List<String> curImage, List<String> addImage, List<String> removeImage) {
        if (CollectionUtils.isEmpty(addImage) && CollectionUtils.isEmpty(removeImage)) {
            return curImage;
        }

        List<String> images = new ArrayList<>(addImage);
        for (String img : curImage) {
            if (!removeImage.contains(img)) {
                images.add(img);
            }
        }

        return images;
    }
}
