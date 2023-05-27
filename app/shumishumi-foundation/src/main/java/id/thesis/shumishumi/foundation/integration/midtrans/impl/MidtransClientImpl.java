package id.thesis.shumishumi.foundation.integration.midtrans.impl;

import com.midtrans.service.MidtransCoreApi;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.PaymentEnum;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;
import id.thesis.shumishumi.foundation.integration.midtrans.MidtransClient;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class MidtransClientImpl implements MidtransClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogConstant.INTEGRATION_LOGGER);

    @Autowired
    private MidtransCoreApi midtransCoreApi;

    @Override
    public JSONObject createPayment(TransactionVO transaction) {
        LogUtil.info(LOGGER, String.format("MidtransClient#createPayment[transaction=%s]", transaction));
        Map<String, String> transactionDetail = new HashMap<>();
        transactionDetail.put(CommonConst.MIDTRANS_GROSS_AMOUNT, String.valueOf(transaction.getPrice()));
        transactionDetail.put(CommonConst.MIDTRANS_ORDER_ID, transaction.getTransactionId());

        List<Map<String, String>> items = transaction.getDetails().stream().map(detail -> {
            ItemVO itemVO = detail.getHistoryItemVO().getItem();

            Map<String, String> itemMap = new HashMap<>();
            itemMap.put("id", itemVO.getItemId());
            itemMap.put("price", String.valueOf(itemVO.getItemPrice()));
            itemMap.put("quantity", String.valueOf(detail.getQuantity()));
            itemMap.put("name", itemVO.getItemName());

            return itemMap;
        }).collect(Collectors.toList());

        Map<String, Object> request = new HashMap<>();
        request.put(CommonConst.MIDTRANS_TRANSACTION_DETAIL, transactionDetail);
        request.put(CommonConst.MIDTRANS_ITEM_DETAILS, items);
        if (StringUtils.equals(transaction.getPaymentType(), PaymentEnum.BCA_VA.getName())) {
            request.put(CommonConst.MIDTRANS_BANK_TRANSFER, bankTransfer(PaymentEnum.BCA_VA.getPaymentAcquirer()));
            request.put(CommonConst.MIDTRANS_PAYMENT_TYPE, PaymentEnum.BCA_VA.getPaymentType());
        }

        LogUtil.info(LOGGER, String.format("MidtransClient#createPayment[JSON Request=%s]", request));

        JSONObject result = new JSONObject();
        try {
            result = midtransCoreApi.chargeTransaction(request);
        } catch (Exception e) {
            LogUtil.exception(e.getMessage(), e);
            throw new ShumishumiException("error when creating transaction to midtrans", ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("MidtransClient#createPayment[result=%s]", result.toString()));

        return result;
    }

    private Map<String, String> bankTransfer(String acquirer) {
        Map<String, String> bankTransfer = new HashMap<>();
        bankTransfer.put(CommonConst.MIDTRANS_BANK, acquirer);

        return bankTransfer;
    }

}
