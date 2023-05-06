package id.thesis.shumishumi.facade.result.item;

import id.thesis.shumishumi.facade.model.summary.ItemSummary;
import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QueryItemDetailResult extends BaseResult {
    private static final long serialVersionUID = -245940623292123734L;

    private ItemSummary item;
}
