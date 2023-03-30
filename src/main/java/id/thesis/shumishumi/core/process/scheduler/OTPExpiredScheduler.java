package id.thesis.shumishumi.core.process.scheduler;

import id.thesis.shumishumi.common.constant.CommonConst;
import id.thesis.shumishumi.core.process.callback.SchedulerCallback;
import id.thesis.shumishumi.core.process.callback.SchedulerCallbackSupport;
import id.thesis.shumishumi.core.service.OTPService;
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
