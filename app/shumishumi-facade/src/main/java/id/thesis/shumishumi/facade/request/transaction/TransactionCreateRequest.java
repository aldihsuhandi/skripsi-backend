package id.thesis.shumishumi.facade.request.transaction;

import id.thesis.shumishumi.facade.model.transferobject.TransactionItem;
import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class TransactionCreateRequest extends BaseRequest {
    private static final long serialVersionUID = -3763822505095502947L;

    private List<TransactionItem> items;
    private boolean fromCart = true;
}
