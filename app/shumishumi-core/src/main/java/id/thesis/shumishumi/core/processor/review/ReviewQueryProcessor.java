package id.thesis.shumishumi.core.processor.review;

import id.thesis.shumishumi.common.service.ReviewService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.converter.SummaryConverter;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.ReviewVO;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.review.ReviewQueryRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.review.ReviewQueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewQueryProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        ReviewQueryRequest request = (ReviewQueryRequest) baseRequest;
        ReviewQueryResult result = (ReviewQueryResult) baseResult;

        PagingContext pagingContext = new PagingContext();
        pagingContext.setPageNumber(request.getPageNumber());
        pagingContext.setNumberOfItem(request.getNumberOfItem());

        List<ReviewVO> reviewVOS = new ArrayList<>();
        if (StringUtils.equals("MERCHANT", request.getType())) {
            UserVO merchant = userService.queryByUsername(request.getMerchantName(), true);
            AssertUtil.isNotNull(merchant, "merchant not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
            AssertUtil.isExpected(merchant.isActive(), "merchant not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
            AssertUtil.isExpected(!merchant.isDeleted(), "merchant not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);

            reviewVOS = reviewService.queryByMerchant(merchant.getUserId(), pagingContext);
        } else {
            SessionVO sessionVO = sessionService.query(request.getSessionId());
            AssertUtil.isNotNull(sessionVO, "session expired", ShumishumiErrorCodeEnum.SESSION_EXPIRED);
            AssertUtil.isExpected(sessionVO.isActive(), "session expired", ShumishumiErrorCodeEnum.SESSION_EXPIRED);
            AssertUtil.isExpected(!sessionVO.isRemembered() && new Date().before(sessionVO.getSessionDt()),
                    "session expired", ShumishumiErrorCodeEnum.SESSION_EXPIRED);
            String userId = sessionVO.getUserId();

            reviewVOS = reviewService.queryByUserAndNeedReview(userId, request.isNeedReview(), pagingContext);
        }

        pagingContext.calculateTotalPage();
        pagingContext.checkHasNext(pagingContext.getTotalItem(), pagingContext.getNumberOfItem());

        result.setPagingContext(pagingContext);
        result.setReviews(reviewVOS.stream().map(SummaryConverter::toSummary)
                .collect(Collectors.toList()));
    }
}
