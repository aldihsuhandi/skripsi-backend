package id.thesis.shumishumi.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
public class CrowdRuleDAO implements Serializable {
    private static final long serialVersionUID = 5737030963847412131L;

    private String ruleId;
    private String crowdId;
    private String ruleType;
    private String ruleValue;
    private Date gmtCreate;
    private Date gmtModified;
}
