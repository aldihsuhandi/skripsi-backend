package id.thesis.shumishumi.facade.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class HistoryItemVO extends BaseVO {
    private static final long serialVersionUID = 1824286013206276331L;

    private String historyItemId;
    private ItemVO item;
}
