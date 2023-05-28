package id.thesis.shumishumi.facade.model.summary;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TransactionDetailSummary extends BaseSummary {
    private HistoryItemSummary item;
    private Integer quantity;
}
