package id.thesis.shumishumi.test.facade;


import id.thesis.shumishumi.facade.api.ReviewFacade;
import id.thesis.shumishumi.facade.model.enumeration.InterestLevelEnum;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.review.ReviewCreateRequest;
import id.thesis.shumishumi.facade.request.review.ReviewQueryRequest;
import id.thesis.shumishumi.facade.result.review.ReviewCreateResult;
import id.thesis.shumishumi.facade.result.review.ReviewQueryResult;
import id.thesis.shumishumi.foundation.model.result.ReviewDO;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ReviewFacadeTest extends FacadeTestBase {

    @Autowired
    private ReviewFacade reviewFacade;

    @BeforeEach
    public void setup() {
        mockitoSession("user");
        mockUserWithRole("MERCHANT");
        mockItemWithInfo();
        Mockito.when(interestLevelDAO.queryByName(Mockito.any())).
                thenReturn(mockInterestLevelDO());
        Mockito.when(interestLevelDAO.queryById(Mockito.any())).
                thenReturn(mockInterestLevelDO());
        Mockito.when(reviewDAO.queryByItemId(Mockito.any())).thenReturn(mockReviewed());
    }

    @Test
    public void createReviewsTest_SUCCESS() {
        ReviewCreateRequest request = new ReviewCreateRequest();
        request.setReviewId("reviewId");
        request.setInterestLevel(InterestLevelEnum.INTERMEDIATE.getLevel());
        request.setReview(4);

        Mockito.when(reviewDAO.queryById(Mockito.any())).thenReturn(mockReviewDO());

        ReviewCreateResult result = reviewFacade.create(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }

    @Test
    public void reviewQueryByUser_SUCCESS() {
        ReviewQueryRequest request = new ReviewQueryRequest();
        request.setNeedReview(false);
        request.setType("USER");

        ReviewQueryResult result = reviewFacade.query(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }

    @Test
    public void reviewQueryByMerchant_SUCCESS() {
        ReviewQueryRequest request = new ReviewQueryRequest();
        request.setNeedReview(false);
        request.setMerchantName("merchantName");
        request.setType("MERCHANT");

        Mockito.when(reviewDAO.queryByMerchant(Mockito.any(), Mockito.any()))
                .thenReturn(mockReviewed());

        ReviewQueryResult result = reviewFacade.query(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }

    public ReviewDO mockReviewDO() {
        ReviewDO reviewDO = new ReviewDO();
        reviewDO.setUserId("user");
        reviewDO.setItemId("item");
        reviewDO.setMerchantId("merchantId");
        reviewDO.setNeedReview(true);
        reviewDO.setStar(-1);

        return reviewDO;
    }

    public List<ReviewDO> mockReviewed() {
        List<ReviewDO> reviews = new ArrayList<>();
        for (int i = 0; i < 5; ++i) {
            ReviewDO reviewDO = new ReviewDO();
            reviewDO.setUserId("user");
            reviewDO.setItemId("item");
            reviewDO.setMerchantId("merchantId");
            reviewDO.setNeedReview(false);
            reviewDO.setInterestLevel("BEGINNER");
            reviewDO.setStar(4);

            reviews.add(reviewDO);
        }
        return reviews;
    }
}
