package id.thesis.shumishumi.foundation.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InterestLevelDO extends BaseDO {
    private static final long serialVersionUID = 203728092658437272L;

    private String interestLevelId;
    private String interestLevelName;
}
