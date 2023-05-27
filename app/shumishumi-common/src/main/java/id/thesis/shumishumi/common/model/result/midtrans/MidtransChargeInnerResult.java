package id.thesis.shumishumi.common.model.result.midtrans;

import id.thesis.shumishumi.common.model.result.BaseInnerResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MidtransChargeInnerResult extends BaseInnerResult {
    private static final long serialVersionUID = 8164655802025656763L;

    private String midtransId;
    private String midtransLink;
    private String paymentType;
    private String paymentStatus;
}
