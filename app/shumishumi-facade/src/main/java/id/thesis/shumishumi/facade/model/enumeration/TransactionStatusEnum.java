package id.thesis.shumishumi.facade.model.enumeration;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum TransactionStatusEnum {

    INIT("Init", "INIT", "initial state"),
    WAITING_PAYMENT("Waiting Payment", "WAITING PAYMENT", "waiting for payment"),
    ONGOING("Ongoing", "ONGOING", "ongoing transaction"),
    DONE("Done", "DONE", "transaction finish"),
    CANCELED("Canceled", "CANCELED", "transaction canceled or expired"),
    ;

    private String name;
    private String code;
    private String displayName;

    TransactionStatusEnum(String name, String code, String displayName) {
        this.name = name;
        this.code = code;
        this.displayName = displayName;
    }

    public static TransactionStatusEnum findByName(String name) {
        for (TransactionStatusEnum e : values()) {
            if (StringUtils.equals(e.getName(), name)) {
                return e;
            }
        }

        return null;
    }
}
