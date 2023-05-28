package id.thesis.shumishumi.service.scheduler;

import id.thesis.shumishumi.common.service.TransactionService;
import id.thesis.shumishumi.core.callback.SchedulerCallback;
import id.thesis.shumishumi.core.callback.SchedulerCallbackSupport;
import id.thesis.shumishumi.facade.model.enumeration.TransactionStatusEnum;
import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionCheckStatusScheduler {

    @Autowired
    private TransactionService transactionService;

    @Scheduled(fixedRate = 60000)
    public void scheduleTask() {
        SchedulerCallbackSupport.process("TransactionCheckStatusScheduler", new SchedulerCallback() {
            @Override
            public void executeTask() {
                List<String> transactionIds = transactionService.queryList(TransactionStatusEnum.WAITING_PAYMENT.getCode()).stream()
                        .map(TransactionVO::getTransactionId).collect(Collectors.toList());

                transactionIds.forEach(id -> transactionService.checkPaymentStatus(id));
            }
        });
    }
}
