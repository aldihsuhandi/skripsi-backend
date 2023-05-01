package id.thesis.shumishumi.facade.request.user.email;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailEncryptRequest extends BaseRequest {
    private static final long serialVersionUID = -1021148265329086204L;

    private String email;
}
