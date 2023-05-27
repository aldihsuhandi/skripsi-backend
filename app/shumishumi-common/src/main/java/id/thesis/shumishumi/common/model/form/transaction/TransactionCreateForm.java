package id.thesis.shumishumi.common.model.form.transaction;

import id.thesis.shumishumi.common.model.form.BaseForm;
import id.thesis.shumishumi.facade.model.transferobject.TransactionItem;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TransactionCreateForm extends BaseForm {
    private static final long serialVersionUID = 7442157064318310165L;

    private List<TransactionItem> items;
}
