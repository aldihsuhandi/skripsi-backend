package id.thesis.shumishumi.core.request.image;

import id.thesis.shumishumi.core.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class ImageUploadRequest extends BaseRequest {
    private static final long serialVersionUID = 5725027971564723973L;

    private MultipartFile image;
}
