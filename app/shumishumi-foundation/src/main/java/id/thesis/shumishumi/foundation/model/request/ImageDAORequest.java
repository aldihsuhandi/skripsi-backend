package id.thesis.shumishumi.foundation.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Blob;

@Getter
@Setter
@ToString
public class ImageDAORequest implements Serializable {
    private static final long serialVersionUID = -3542678077486760617L;

    private String imageId;
    private String imageName;
    private String imageExt;
    private Blob image;
}
