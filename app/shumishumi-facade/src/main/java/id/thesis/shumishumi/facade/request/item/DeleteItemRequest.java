package id.thesis.shumishumi.facade.request.item;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeleteItemRequest extends BaseRequest {
    private static final long serialVersionUID = -1668886433605870298L;

    private String itemId;
}
