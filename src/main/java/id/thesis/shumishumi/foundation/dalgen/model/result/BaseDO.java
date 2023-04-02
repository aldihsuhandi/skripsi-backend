package id.thesis.shumishumi.foundation.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class BaseDO implements Serializable {
    private Date gmtCreate;
    private Date gmtModified;
}
