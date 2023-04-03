package id.thesis.shumishumi.foundation.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class UserCrowdDO implements Serializable {
    private static final long serialVersionUID = -5163854961644413267L;

    private String crowdId;
    private String userId;
    private Date gmtCreate;
    private Date gmtModified;
}
