package id.thesis.shumishumi.foundation.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CrowdDO extends BaseDO {
    private static final long serialVersionUID = -8725075864930039934L;

    private String crowdId;
    private String isActive;
}
