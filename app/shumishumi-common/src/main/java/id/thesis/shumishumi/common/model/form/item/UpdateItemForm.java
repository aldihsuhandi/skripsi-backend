package id.thesis.shumishumi.common.model.form.item;

import id.thesis.shumishumi.common.model.form.BaseForm;
import id.thesis.shumishumi.facade.model.context.ItemUpdateContext;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateItemForm extends BaseForm {
    private static final long serialVersionUID = -8374688001464185725L;

    private String itemId;
    private ItemUpdateContext itemUpdateContext;
}
