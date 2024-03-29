package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.result.midtrans.MidtransChargeInnerResult;
import id.thesis.shumishumi.common.service.MidtransService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;
import id.thesis.shumishumi.foundation.integration.midtrans.MidtransClient;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MidtransServiceImpl implements MidtransService {

    @Autowired
    private MidtransClient midtransClient;

    @Override
    public MidtransChargeInnerResult createPayment(TransactionVO transaction) {
        JSONObject chargeResult = midtransClient.createPayment(transaction);

        AssertUtil.isExpected(chargeResult.get("status_code"), "201", "Midtrans API Integration error", ShumishumiErrorCodeEnum.SYSTEM_ERROR);

        MidtransChargeInnerResult result = new MidtransChargeInnerResult();
        result.setMidtransId(chargeResult.getString("transaction_id"));
        result.setPaymentType(chargeResult.getString("payment_type"));
        result.setPaymentStatus(chargeResult.getString("transaction_status"));

        if (StringUtils.equals(result.getPaymentType(), "bank_transfer")) {
            JSONArray vaNumbers = chargeResult.getJSONArray("va_numbers");
            AssertUtil.isExpected(!vaNumbers.isEmpty(), "Midtrans API result malformed", ShumishumiErrorCodeEnum.SYSTEM_ERROR);
            JSONObject object = vaNumbers.getJSONObject(0);
            result.setMidtransLink(object.getString("va_number"));
        }

        return result;
    }

    @Override
    public void cancelPayment(String transactionId) {
        JSONObject cancelResult = midtransClient.cancelPayment(transactionId);
        String statusCode = cancelResult.getString("status_code");
        String transactionStatus = cancelResult.getString("transaction_status");

        AssertUtil.isExpected(StringUtils.equals(statusCode, "200"),
                "Midtrans API Integration error", ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        AssertUtil.isExpected(StringUtils.equals(transactionStatus, "cancel"),
                "Midtrans API Integration error", ShumishumiErrorCodeEnum.SYSTEM_ERROR);
    }

    @Override
    public String checkStatus(String transactionId) {
        JSONObject statusResult = midtransClient.checkStatus(transactionId);
        String statusCode = statusResult.getString("status_code");
        AssertUtil.isExpected(StringUtils.equals(statusCode, "201") || StringUtils.equals(statusCode, "200"),
                "Midtrans API Integration error", ShumishumiErrorCodeEnum.SYSTEM_ERROR);

        return statusResult.getString("transaction_status");
    }
}
