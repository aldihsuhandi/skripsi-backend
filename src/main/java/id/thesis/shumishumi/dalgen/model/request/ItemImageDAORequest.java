package id.thesis.shumishumi.dalgen.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;

@Getter
@Setter
@ToString
public class ItemImageDAORequest implements Serializable {
    private static final long serialVersionUID = 3621502270204880948L;

    private String itemImageId;
    private String itemId;
    private Blob itemImage;
    private Date gmtCreate;
    private Date gmtModified;
}
