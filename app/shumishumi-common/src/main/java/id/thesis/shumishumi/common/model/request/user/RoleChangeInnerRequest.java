package id.thesis.shumishumi.common.model.request.user;

import id.thesis.shumishumi.common.model.request.BaseInnerRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class RoleChangeInnerRequest extends BaseInnerRequest {
    private String userId;
    private String userRole;
}
