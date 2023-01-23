package id.thesis.shumishumi.rest.request.item;

import id.thesis.shumishumi.rest.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemApprovalRequest extends BaseRequest {
    private static final long serialVersionUID = 3433454687745168991L;

    private String itemId;
}
