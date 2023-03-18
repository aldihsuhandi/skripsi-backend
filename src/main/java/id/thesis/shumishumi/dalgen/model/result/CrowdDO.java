package id.thesis.shumishumi.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class CrowdDO implements Serializable {
    private static final long serialVersionUID = -8725075864930039934L;

    private String crowdId;
    private String isActive;
    private Date gmtCreate;
    private Date gmtModified;
}
