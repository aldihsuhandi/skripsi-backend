package id.thesis.shumishumi.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class ItemCrowdDO implements Serializable {
    private static final long serialVersionUID = 3832624843568011286L;

    private String itemId;
    private String crowdId;
    private Date gmtCreate;
    private Date gmtModified;
}
