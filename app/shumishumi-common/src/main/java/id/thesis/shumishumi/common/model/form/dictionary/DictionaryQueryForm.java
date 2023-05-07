package id.thesis.shumishumi.common.model.form.dictionary;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DictionaryQueryForm extends BaseForm {
    private static final long serialVersionUID = -8199351659964083437L;

    private String dictionaryKey;
}
