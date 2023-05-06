package id.thesis.shumishumi.facade.request.item;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QueryItemDetailRequest extends BaseRequest {
    private static final long serialVersionUID = -8952072358603585700L;

    private String itemId;
}
