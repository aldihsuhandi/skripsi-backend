package id.thesis.shumishumi.foundation.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Blob;

@Getter
@Setter
@ToString
public class ImageDO extends BaseDO {
    private static final long serialVersionUID = 3824433572431581840L;

    private String imageId;
    private String imageName;
    private String imageExt;
    private Blob image;
}
