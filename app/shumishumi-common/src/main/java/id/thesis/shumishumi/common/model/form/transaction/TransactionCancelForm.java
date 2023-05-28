package id.thesis.shumishumi.common.model.form.transaction;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionCancelForm extends BaseForm {
    private static final long serialVersionUID = 3397567509602840908L;

    private String transactionId;
}
