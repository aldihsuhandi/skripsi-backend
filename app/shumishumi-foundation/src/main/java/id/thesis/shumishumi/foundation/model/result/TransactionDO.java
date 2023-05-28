package id.thesis.shumishumi.foundation.model.result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction")
public class TransactionDO extends BaseDO {
    private static final long serialVersionUID = -93603629059231539L;

    @Id
    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "price")
    private Long price;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "midtrans_id")
    private String midtransId;

    @Column(name = "midtrans_link")
    private String midtransLink;

    @Column(name = "status")
    private String status;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getMidtransId() {
        return midtransId;
    }

    public void setMidtransId(String midtransId) {
        this.midtransId = midtransId;
    }

    public String getMidtransLink() {
        return midtransLink;
    }

    public void setMidtransLink(String midtransLink) {
        this.midtransLink = midtransLink;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
