package id.thesis.shumishumi.service.scheduler;

import id.thesis.shumishumi.common.service.TransactionService;
import id.thesis.shumishumi.core.callback.SchedulerCallback;
import id.thesis.shumishumi.core.callback.SchedulerCallbackSupport;
import id.thesis.shumishumi.facade.model.enumeration.TransactionStatusEnum;
import id.thesis.shumishumi.facade.model.viewobject.TransactionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class TransactionExpiredScheduler {

    @Autowired
    private TransactionService transactionService;

    @Scheduled(fixedRate = 86400000)
    public void scheduleTask() {
        SchedulerCallbackSupport.process("TransactionExpiredScheduler", new SchedulerCallback() {
            @Override
            public void executeTask() {
                List<String> transactionIds = transactionService.queryList(TransactionStatusEnum.INIT.getCode()).stream()
                        .filter(transaction -> {
                            LocalDate transactionDate = transaction.getGmtCreate().toInstant().
                                    atZone(ZoneId.systemDefault()).toLocalDate();
                            LocalDate currentDate = (new Date()).toInstant().atZone(
                                    ZoneId.systemDefault()).toLocalDate();

                            long day = ChronoUnit.DAYS.between(transactionDate, currentDate);
                            return day >= 1;
                        })
                        .map(TransactionVO::getTransactionId)
                        .collect(Collectors.toList());

                transactionIds.forEach(id -> transactionService.delete(id));
            }
        });
    }
}
