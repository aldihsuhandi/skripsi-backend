package id.thesis.shumishumi.facade.model.viewobject;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class BaseVO implements Serializable {
    private static final long serialVersionUID = -271091669774834388L;

    private Date gmtCreate;
    private Date gmtModified;
}
