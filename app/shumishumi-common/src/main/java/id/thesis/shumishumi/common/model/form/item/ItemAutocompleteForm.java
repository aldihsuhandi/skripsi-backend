package id.thesis.shumishumi.common.model.form.item;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemAutocompleteForm extends BaseForm {
    private static final long serialVersionUID = -947113844100703400L;

    private String autocomplete;
}
