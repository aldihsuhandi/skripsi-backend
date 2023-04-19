package id.thesis.shumishumi.common.model.form.image;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class ImageUploadForm extends BaseForm {
    private static final long serialVersionUID = 7790940052240184725L;

    private MultipartFile image;
}
