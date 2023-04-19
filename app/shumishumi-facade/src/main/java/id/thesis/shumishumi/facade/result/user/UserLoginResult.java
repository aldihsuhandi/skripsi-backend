package id.thesis.shumishumi.facade.result.user;

import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginResult extends BaseResult {
    private String sessionId;
}
