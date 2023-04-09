package id.thesis.shumishumi.core.request.image;

import id.thesis.shumishumi.core.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ImageDownloadRequest extends BaseRequest {
    private static final long serialVersionUID = -6426329426410353240L;

    private String imageId;
}
