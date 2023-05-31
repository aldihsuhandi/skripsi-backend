package id.thesis.shumishumi.core.processor.review;

import id.thesis.shumishumi.common.service.ReviewService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.core.converter.SummaryConverter;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.viewobject.ReviewVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.review.ReviewQueryRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.review.ReviewQueryResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ReviewQueryProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ReviewService reviewService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        ReviewQueryRequest request = (ReviewQueryRequest) baseRequest;
        ReviewQueryResult result = (ReviewQueryResult) baseResult;

        String userId = sessionService.query(request.getSessionId()).getUserId();

        PagingContext pagingContext = new PagingContext();
        pagingContext.setPageNumber(request.getPageNumber());
        pagingContext.setNumberOfItem(request.getNumberOfItem());

        List<ReviewVO> reviewVOS = new ArrayList<>();
        if (StringUtils.equals("MERCHANT", request.getType())) {
            reviewVOS = reviewService.queryByMerchant(userId, pagingContext);
        } else {
            reviewVOS = reviewService.queryByUserAndNeedReview(userId, request.isNeedReview(), pagingContext);
        }

        pagingContext.calculateTotalPage();
        pagingContext.checkHasNext(pagingContext.getTotalItem(), pagingContext.getNumberOfItem());

        result.setPagingContext(pagingContext);
        result.setReviews(reviewVOS.stream().map(SummaryConverter::toSummary)
                .collect(Collectors.toList()));
    }
}
