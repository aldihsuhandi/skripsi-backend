package id.thesis.shumishumi.core.result.image;

import id.thesis.shumishumi.core.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class ImageDownloadResult extends BaseResult {
    private static final long serialVersionUID = -3848283165803867092L;

    private MultipartFile image;
}
