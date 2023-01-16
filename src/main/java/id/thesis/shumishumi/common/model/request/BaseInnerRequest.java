package id.thesis.shumishumi.common.model.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class BaseInnerRequest implements Serializable {
    private static final long serialVersionUID = -7610495984092807406L;
    private Date gmtCreate;
    private Date gmtModified;
}
