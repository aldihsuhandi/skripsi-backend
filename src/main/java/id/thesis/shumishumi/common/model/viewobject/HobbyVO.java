package id.thesis.shumishumi.common.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HobbyVO extends BaseVO {
    private static final long serialVersionUID = 3516110225244239877L;

    private String hobbyId;
    private String hobbyName;
}
