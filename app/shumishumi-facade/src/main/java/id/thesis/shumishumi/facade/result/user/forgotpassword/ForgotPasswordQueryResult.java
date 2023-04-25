package id.thesis.shumishumi.facade.result.user.forgotpassword;

import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ForgotPasswordQueryResult extends BaseResult {
    private static final long serialVersionUID = -1234169767149021759L;

    private String email;
}
