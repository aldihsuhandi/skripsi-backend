package id.thesis.shumishumi.facade.model.enumeration;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

@Getter
public enum PaymentEnum {
    QRIS("Qris", "qris", "gopay"),
    BCA_VA("BCA Virtual Account", "bank_transfer", "bca"),
    ;

    PaymentEnum(String name, String paymentType, String paymentAcquirer) {
        this.name = name;
        this.paymentType = paymentType;
        this.paymentAcquirer = paymentAcquirer;
    }

    private String name;
    private String paymentType;
    private String paymentAcquirer;

    public static PaymentEnum findByName(String name) {
        for (PaymentEnum pe : values()) {
            if (StringUtils.equals(pe.getName(), name)) {
                return pe;
            }
        }
        return null;
    }

    public static PaymentEnum findByTypeAndAcquirer(String type, String acquirer) {
        for (PaymentEnum pe : values()) {
            if (StringUtils.equals(pe.getPaymentAcquirer(), acquirer) &&
                    StringUtils.equals(pe.getPaymentType(), type)) {
                return pe;
            }
        }
        return null;
    }
}
