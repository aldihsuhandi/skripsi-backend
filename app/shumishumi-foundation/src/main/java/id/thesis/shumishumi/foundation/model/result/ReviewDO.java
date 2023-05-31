package id.thesis.shumishumi.foundation.model.result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "reviews")
public class ReviewDO extends BaseDO {
    private static final long serialVersionUID = 2009618675072126529L;

    @Id
    @Column(name = "review_id")
    private String reviewId;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "description")
    private String description;

    @Column(name = "review_images")
    private String reviewImages;

    @Column(name = "interest_level")
    private String interestLevel;

    @Column(name = "star")
    private Integer star;

    @Column(name = "need_review")
    private Boolean needReview;

    public String getReviewId() {
        return reviewId;
    }

    public void setReviewId(String reviewId) {
        this.reviewId = reviewId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReviewImages() {
        return reviewImages;
    }

    public void setReviewImages(String reviewImages) {
        this.reviewImages = reviewImages;
    }

    public String getInterestLevel() {
        return interestLevel;
    }

    public void setInterestLevel(String interestLevel) {
        this.interestLevel = interestLevel;
    }

    public Integer getStar() {
        return star;
    }

    public void setStar(Integer star) {
        this.star = star;
    }

    public Boolean getNeedReview() {
        return needReview;
    }

    public void setNeedReview(Boolean needReview) {
        this.needReview = needReview;
    }
}
