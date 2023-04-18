package id.thesis.shumishumi.common.model.form.image;

import id.thesis.shumishumi.common.model.form.BaseForm;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageDownloadForm extends BaseForm {
    private static final long serialVersionUID = -4041208542183014976L;

    private String imageId;
}
