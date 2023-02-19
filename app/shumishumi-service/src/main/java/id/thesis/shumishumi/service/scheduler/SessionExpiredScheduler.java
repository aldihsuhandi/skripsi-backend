/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.service.scheduler;

import id.thesis.shumishumi.common.constant.CommonConst;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.core.callback.SchedulerCallback;
import id.thesis.shumishumi.core.callback.SchedulerCallbackSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: SessionExpiredScheduler.java, v 0.1 2023‐01‐13 11:16 Aldih Suhandi Exp $$
 */

@Component
public class SessionExpiredScheduler {

    @Autowired
    private SessionService sessionService;

    @Scheduled(fixedRate = 300000)
    public void scheduleTask() {
        SchedulerCallbackSupport.process(CommonConst.EXPIRED_SESSION_SCHEDULER, new SchedulerCallback() {
            @Override
            public void executeTask() {
                sessionService.deactivateExpiredSession();
            }

            @Override
            public void composeLog() {
                return;
            }
        });
    }
}
