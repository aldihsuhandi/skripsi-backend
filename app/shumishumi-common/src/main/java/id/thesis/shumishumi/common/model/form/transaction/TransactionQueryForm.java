package id.thesis.shumishumi.common.model.form.transaction;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionQueryForm extends BaseForm {
    private static final long serialVersionUID = -1297978821319846279L;

    private String status;
    private int pageNumber = 1;
    private int numberOfItems = 10;
}
