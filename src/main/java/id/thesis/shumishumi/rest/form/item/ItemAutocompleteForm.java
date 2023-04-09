package id.thesis.shumishumi.rest.form.item;

import id.thesis.shumishumi.rest.form.BaseForm;
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
