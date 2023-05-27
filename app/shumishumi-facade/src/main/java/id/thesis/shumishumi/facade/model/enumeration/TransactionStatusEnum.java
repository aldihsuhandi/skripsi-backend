package id.thesis.shumishumi.facade.model.enumeration;

import lombok.Getter;

@Getter
public enum TransactionStatusEnum {

    INIT("INIT", "initial state"),
    WAITING_PAYMENT("WAITING PAYMENT", "waiting for payment"),
    ONGOING("ONGOING", "ongoing transaction"),
    DONE("DONE", "transaction finish"),
    CANCELED("CANCELED", "transaction canceled or expired"),
    ;

    private String code;
    private String displayName;

    TransactionStatusEnum(String code, String displayName) {
        this.code = code;
        this.displayName = displayName;
    }
}
