package id.thesis.shumishumi.foundation.model.result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Blob;

@Entity
@Table(name = "images")
public class ImageDO extends BaseDO {
    private static final long serialVersionUID = 3824433572431581840L;

    @Id
    @Column(name = "image_id")
    private String imageId;

    @Column(name = "image_name")
    private String imageName;

    @Column(name = "image_ext")
    private String imageExt;

    @Column(name = "image")
    private Blob image;

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getImageExt() {
        return imageExt;
    }

    public void setImageExt(String imageExt) {
        this.imageExt = imageExt;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }
}
