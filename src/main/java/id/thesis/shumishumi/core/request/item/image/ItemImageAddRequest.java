package id.thesis.shumishumi.core.request.item.image;

import id.thesis.shumishumi.core.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@ToString
public class ItemImageAddRequest extends BaseRequest {
    private static final long serialVersionUID = -4986412268659803893L;

    private String itemId;
    private MultipartFile image;
}
