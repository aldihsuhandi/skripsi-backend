package id.thesis.shumishumi.core.result.image;

import id.thesis.shumishumi.core.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageUploadResult extends BaseResult {
    private static final long serialVersionUID = 1088297187262346865L;

    private String imageId;
}
