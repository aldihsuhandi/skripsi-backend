package id.thesis.shumishumi.rest.form.item.image;

import id.thesis.shumishumi.rest.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class AddItemImageForm extends BaseForm {
    private static final long serialVersionUID = -3455536363369659643L;

    private String itemIds;
    private MultipartFile image;
}
