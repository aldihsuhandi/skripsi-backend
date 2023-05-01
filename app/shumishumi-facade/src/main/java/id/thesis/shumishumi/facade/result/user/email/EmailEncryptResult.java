package id.thesis.shumishumi.facade.result.user.email;

import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailEncryptResult extends BaseResult {
    private static final long serialVersionUID = -5802448439171123913L;

    private String uuid;
}
