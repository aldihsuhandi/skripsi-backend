package id.thesis.shumishumi.foundation.integration.midtrans;

import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;
import org.json.JSONObject;

public interface MidtransClient {
    JSONObject createPayment(TransactionVO transaction);

    JSONObject cancelPayment(String midtransId);

    JSONObject checkStatus(String midtransId);
}
