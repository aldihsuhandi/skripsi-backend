package id.thesis.shumishumi.rest.result.user;

import id.thesis.shumishumi.rest.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserLoginResult extends BaseResult {
    private String sessionId;
}
