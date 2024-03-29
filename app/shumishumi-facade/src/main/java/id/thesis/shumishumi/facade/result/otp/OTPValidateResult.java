package id.thesis.shumishumi.facade.result.otp;

import id.thesis.shumishumi.facade.result.BaseResult;
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
