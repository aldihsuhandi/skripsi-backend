package id.thesis.shumishumi.rest.form.item;

import id.thesis.shumishumi.common.model.context.ItemFilterContext;
import id.thesis.shumishumi.rest.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QueryItemForm extends BaseForm {
    private static final long serialVersionUID = 6861507447162195909L;

    private ItemFilterContext itemFilterContext = new ItemFilterContext();
    private int pageNumber = 1;
    private int numberOfItem = 10;
}
