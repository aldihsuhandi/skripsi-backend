package id.thesis.shumishumi.facade.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class InterestLevelVO extends BaseVO {
    private static final long serialVersionUID = 5450324046256207705L;

    private String interestLevelId;
    private String interestLevelName;
}
