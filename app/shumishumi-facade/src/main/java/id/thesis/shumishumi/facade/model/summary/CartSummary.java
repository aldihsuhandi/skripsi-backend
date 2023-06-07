package id.thesis.shumishumi.facade.model.summary;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CartSummary extends BaseSummary {
    private static final long serialVersionUID = 2876862596614768425L;

    private ItemSummary itemSummary;
    private int quantity;
    private boolean selected;
}
