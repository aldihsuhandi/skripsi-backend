package id.thesis.shumishumi.foundation.model.result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_details")
public class TransactionDetailDO extends BaseDO {

    @Id
    @Column(name = "transaction_detail_id")
    private String transactionDetailId;

    @Column(name = "transaction_id")
    private String transactionId;

    @Column(name = "history_item_id")
    private String historyItemId;

    @Column(name = "quantity")
    private int quantity;

    public String getTransactionDetailId() {
        return transactionDetailId;
    }

    public void setTransactionDetailId(String transactionDetailId) {
        this.transactionDetailId = transactionDetailId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getHistoryItemId() {
        return historyItemId;
    }

    public void setHistoryItemId(String historyItemId) {
        this.historyItemId = historyItemId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
