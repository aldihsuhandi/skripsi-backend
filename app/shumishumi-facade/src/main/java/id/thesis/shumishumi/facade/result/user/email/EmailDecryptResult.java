package id.thesis.shumishumi.facade.result.user.email;

import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailDecryptResult extends BaseResult {
    private static final long serialVersionUID = -2219894644943305797L;

    private String email;
}
