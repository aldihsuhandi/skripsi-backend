package id.thesis.shumishumi.foundation.model.result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "history_items")
public class HistoryItemDO extends BaseDO {
    private static final long serialVersionUID = 7292862649357858666L;

    @Id
    @Column(name = "history_item_id")
    private String historyItemId;

    @Column(name = "item_id")
    private String itemId;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "item_price")
    private Long itemPrice;

    @Column(name = "item_description")
    private String itemDescription;

    @Column(name = "item_images")
    private String itemImages;

    @Column(name = "category_id")
    private String categoryId;

    @Column(name = "hobby_id")
    private String hobbyId;

    @Column(name = "merchant_id")
    private String merchantId;

    @Column(name = "merchant_level_id")
    private String merchantLevelId;

    @Column(name = "user_level_id")
    private String userLevelId;

    public String getHistoryItemId() {
        return historyItemId;
    }

    public void setHistoryItemId(String historyItemId) {
        this.historyItemId = historyItemId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Long getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Long itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemImages() {
        return itemImages;
    }

    public void setItemImages(String itemImages) {
        this.itemImages = itemImages;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(String hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantLevelId() {
        return merchantLevelId;
    }

    public void setMerchantLevelId(String merchantLevelId) {
        this.merchantLevelId = merchantLevelId;
    }

    public String getUserLevelId() {
        return userLevelId;
    }

    public void setUserLevelId(String userLevelId) {
        this.userLevelId = userLevelId;
    }
}
