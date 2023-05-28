package id.thesis.shumishumi.common.model.form.transaction;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionQueryDetailForm extends BaseForm {
    private static final long serialVersionUID = 6639618059592098514L;

    private String transactionId;
}
