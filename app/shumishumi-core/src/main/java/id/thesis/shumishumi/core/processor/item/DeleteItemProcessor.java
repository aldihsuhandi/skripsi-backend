package id.thesis.shumishumi.core.processor.item;

import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.KnowledgeService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.item.DeleteItemRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

public class DeleteItemProcessor implements BaseProcessor {

    @Autowired
    private ItemService itemService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private KnowledgeService knowledgeService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        DeleteItemRequest request = (DeleteItemRequest) baseRequest;

        ItemVO item = itemService.queryById(request.getItemId(), true);
        knowledgeService.removeItemFromKnowledge(item);

        String userId = sessionService.query(request.getSessionId()).getUserId();

        AssertUtil.isNotNull(item, "item not found", ShumishumiErrorCodeEnum.ITEM_NOT_FOUND);
        AssertUtil.isExpected(!item.isDeleted(), "item not found", ShumishumiErrorCodeEnum.ITEM_NOT_FOUND);
        AssertUtil.isExpected(userId, item.getMerchantInfo().getUserId(), "user is invalid", ShumishumiErrorCodeEnum.USER_INVALID);

        itemService.delete(request.getItemId());
        itemService.refreshCache(Collections.singletonList(request.getItemId()), false);
    }
}
