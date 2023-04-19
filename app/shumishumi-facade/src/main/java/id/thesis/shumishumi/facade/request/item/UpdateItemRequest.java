package id.thesis.shumishumi.facade.request.item;

import id.thesis.shumishumi.facade.model.context.ItemUpdateContext;
import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateItemRequest extends BaseRequest {
    private static final long serialVersionUID = -1269091453999434483L;
    private String itemId;
    private ItemUpdateContext itemUpdateContext;
}
