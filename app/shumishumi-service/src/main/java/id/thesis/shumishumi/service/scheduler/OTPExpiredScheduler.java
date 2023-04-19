package id.thesis.shumishumi.service.scheduler;

import id.thesis.shumishumi.common.service.OTPService;
import id.thesis.shumishumi.core.callback.SchedulerCallback;
import id.thesis.shumishumi.core.callback.SchedulerCallbackSupport;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OTPExpiredScheduler {
    @Autowired
    private OTPService otpService;

    @Scheduled(fixedRate = 600000)
    public void scheduleTask() {
        SchedulerCallbackSupport.process(CommonConst.EXPIRED_OTP_SCHEDULER, new SchedulerCallback() {
            @Override
            public void executeTask() {
                otpService.deactivate();
            }
        });
    }
}
