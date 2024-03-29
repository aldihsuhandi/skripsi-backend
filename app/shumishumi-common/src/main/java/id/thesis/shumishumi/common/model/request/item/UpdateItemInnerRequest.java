package id.thesis.shumishumi.common.model.request.item;

import id.thesis.shumishumi.common.model.request.BaseInnerRequest;
import id.thesis.shumishumi.facade.model.context.ItemUpdateContext;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateItemInnerRequest extends BaseInnerRequest {
    private static final long serialVersionUID = -1185639958728527874L;

    private String itemId;
    private ItemUpdateContext updateContext;
}
