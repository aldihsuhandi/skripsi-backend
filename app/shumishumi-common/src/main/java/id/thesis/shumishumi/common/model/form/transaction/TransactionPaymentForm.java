package id.thesis.shumishumi.common.model.form.transaction;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionPaymentForm extends BaseForm {
    private static final long serialVersionUID = -5715795908730476407L;

    private String paymentType;
    private String transactionId;
}
