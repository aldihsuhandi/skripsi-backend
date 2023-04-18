package id.thesis.shumishumi.facade.model.viewobject;

import id.thesis.shumishumi.common.util.FunctionUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import java.sql.Blob;

@Getter
@Setter
@ToString
public class ImageVO extends BaseVO {
    private static final long serialVersionUID = 1598070133225544810L;

    private String imageId;
    private String imageName;
    private String imageExt;
    private Blob image;

    public ImageVO(MultipartFile multipartFile) {
        if (multipartFile == null) {
            return;
        }

        this.imageId = FunctionUtil.generateUUID();
        this.imageName = multipartFile.getOriginalFilename();
        this.imageExt = multipartFile.getContentType();
        this.image = FunctionUtil.convertToBlob(multipartFile);
    }

    public ImageVO() {
        ;
    }
}
