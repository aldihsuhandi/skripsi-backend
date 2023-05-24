package id.thesis.shumishumi.foundation.integration.midtrans.impl;

import com.midtrans.service.MidtransCoreApi;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;
import id.thesis.shumishumi.foundation.integration.midtrans.MidtransClient;
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
    public void createPayment(TransactionVO transaction) {
        LogUtil.info(LOGGER, String.format("MidtransClient#createPayment[transaction=%s]", transaction));
        Map<String, String> transactionDetail = new HashMap<>();
        transactionDetail.put(CommonConst.MIDTRANS_GROSS_AMOUNT, String.valueOf(transaction.getPrice()));
        transactionDetail.put(CommonConst.MIDTRANS_ORDER_ID, transaction.getTransactionId());

        List<Map<String, String>> items = transaction.getDetails().stream().map(detail -> {
            ItemVO itemVO = detail.getItem();

            Map<String, String> itemMap = new HashMap<>();
            itemMap.put("id", itemVO.getItemId());
            itemMap.put("price", String.valueOf(itemVO.getItemPrice()));
            itemMap.put("quantity", String.valueOf(detail.getQuantity()));
            itemMap.put("name", itemVO.getItemName());
            itemMap.put("category", itemVO.getItemCategory().getCategoryName());
            itemMap.put("merchant_name", itemVO.getMerchantInfo().getUserId());

            return itemMap;
        }).collect(Collectors.toList());

        Map<String, Object> request = new HashMap<>();
        request.put(CommonConst.MIDTRANS_TRANSACTION_DETAIL, transactionDetail);
        request.put(CommonConst.MIDTRANS_ITEM_DETAILS, items);
        JSONObject result;
        try {
            result = midtransCoreApi.chargeTransaction(request);
        } catch (Exception e) {
            throw new ShumishumiException("error when creating transaction to midtrans", ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(LOGGER, String.format("MidtransClient#createPayment[result=%s]", result.toString()));
    }

}
