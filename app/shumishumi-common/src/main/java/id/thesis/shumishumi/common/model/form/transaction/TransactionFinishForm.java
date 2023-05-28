package id.thesis.shumishumi.common.model.form.transaction;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionFinishForm extends BaseForm {
    private static final long serialVersionUID = -8457401725364488544L;

    private String transactionId;
}
