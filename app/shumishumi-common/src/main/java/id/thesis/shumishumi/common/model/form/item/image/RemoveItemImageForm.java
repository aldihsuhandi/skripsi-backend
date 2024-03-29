package id.thesis.shumishumi.common.model.form.item.image;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class RemoveItemImageForm extends BaseForm {
    private static final long serialVersionUID = 8655820146349220306L;

    private String itemId;
    private List<String> imageIds;
}
