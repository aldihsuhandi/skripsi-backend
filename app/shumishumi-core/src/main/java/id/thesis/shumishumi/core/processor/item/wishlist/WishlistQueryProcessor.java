package id.thesis.shumishumi.core.processor.item.wishlist;

import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.ItemWishlistService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.converter.SummaryConverter;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.summary.ItemSummary;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.model.viewobject.ItemWishlistVO;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.item.wishlist.QueryWishlistRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.item.wishlist.QueryWishlistResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WishlistQueryProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemWishlistService itemWishlistService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        QueryWishlistRequest request = (QueryWishlistRequest) baseRequest;
        QueryWishlistResult result = (QueryWishlistResult) baseResult;
        SessionVO sessionVO = sessionService.query(request.getSessionId());
        UserVO userVO = userService.queryById(sessionVO.getUserId(), true);

        validateUser(userVO);

        List<String> itemIds = itemWishlistService.queryUserWishlist(userVO.getUserId())
                .stream().map(ItemWishlistVO::getItemId).collect(Collectors.toList());

        List<ItemVO> filteredItem = itemIds.stream()
                .map(itemId -> itemService.queryById(itemId, true))
                .filter(item -> FunctionUtil.itemFilter(item, request.getItemFilterContext()))
                .collect(Collectors.toList());

        PagingContext pagingContext = new PagingContext(request.getPageNumber(),
                request.getNumberOfItem(), (long) filteredItem.size());

        List<ItemSummary> itemResult = new ArrayList<>();
        int sz = Math.min(pagingContext.getNumberOfItem() + pagingContext.calculateOffset(), filteredItem.size());
        for (int i = Math.max(pagingContext.calculateOffset() - 1, 0); i < sz; ++i) {
            itemResult.add(SummaryConverter.toSummary(filteredItem.get(i)));
        }

        result.setPagingContext(pagingContext);
        result.setWishlistItems(itemResult);
    }

    private void validateUser(UserVO userVO) {
        AssertUtil.isNotNull(userVO, "user not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(userVO.isActive(), "user not active", ShumishumiErrorCodeEnum.USER_NOT_ACTIVE);
        AssertUtil.isExpected(!userVO.isDeleted(), "user not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
    }
}
