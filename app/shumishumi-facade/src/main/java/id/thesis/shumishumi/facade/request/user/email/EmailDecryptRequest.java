package id.thesis.shumishumi.facade.request.user.email;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmailDecryptRequest extends BaseRequest {
    private static final long serialVersionUID = 3050039887400499774L;

    private String uuid;
}
