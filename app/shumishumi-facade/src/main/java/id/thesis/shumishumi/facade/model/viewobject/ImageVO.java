package id.thesis.shumishumi.facade.model.viewobject;

import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.util.UUID;

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

        this.imageId = UUID.randomUUID().toString();
        this.imageName = multipartFile.getOriginalFilename();
        this.imageExt = multipartFile.getContentType();
        this.image = this.convertToBlob(multipartFile);
    }

    public ImageVO() {
        ;
    }

    private Blob convertToBlob(MultipartFile multipartFile) {
        if (multipartFile == null) {
            return null;
        }

        try {
            return new SerialBlob(multipartFile.getBytes());
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }
}
