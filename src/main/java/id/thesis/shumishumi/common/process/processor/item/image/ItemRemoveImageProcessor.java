package id.thesis.shumishumi.common.process.processor.item.image;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.common.process.processor.BaseProcessor;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.item.image.ItemImageRemoveRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.service.ItemService;
import id.thesis.shumishumi.core.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ItemRemoveImageProcessor implements BaseProcessor {

    @Autowired
    private ItemService itemService;

    @Autowired
    private SessionService sessionService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        ItemImageRemoveRequest request = (ItemImageRemoveRequest) baseRequest;

        SessionVO sessionVO = sessionService.query(request.getSessionId());
        ItemVO itemVO = itemService.queryById(request.getItemId(), true);

        AssertUtil.isNotNull(itemVO, "item not found", ShumishumiErrorCodeEnum.ITEM_NOT_FOUND);
        AssertUtil.isExpected(sessionVO.getUserId(), itemVO.getMerchantInfo().getUserId()
                , "the current user is not valid for this operation", ShumishumiErrorCodeEnum.USER_INVALID);

        List<String> itemImages = itemVO.getItemImages();
        request.getImageIds().forEach(itemImages::remove);
        itemService.updatePicture(itemVO.getItemId(), itemImages);

        itemService.refreshCache(new ArrayList<>(Collections.singletonList(itemVO.getItemId())), false);
    }
}
