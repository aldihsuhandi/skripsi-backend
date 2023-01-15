package id.thesis.shumishumi.rest.result.otp;

import id.thesis.shumishumi.rest.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OTPValidateResult extends BaseResult {
    private static final long serialVersionUID = 1448190021969303943L;

    boolean isActive = false;
}
