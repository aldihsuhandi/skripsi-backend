/**
 * Dana.id
 * Copyright (c) 2017‐2023 All Rights Reserved.
 */
package id.thesis.shumishumi.process.callback;

import com.fasterxml.jackson.databind.DatabindException;

import java.util.Date;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: SchedulerCallbackSupport.java, v 0.1 2023‐01‐13 11:52 Aldih Suhandi Exp $$
 */
public class SchedulerCallbackSupport {
    public static void process(final String schedulerName, final SchedulerCallback callback) {
        Date beginDate = new Date();
        System.out.printf("[%s] start executing %s scheduler\n", beginDate.toString(), schedulerName);

        callback.executeTask();

        callback.composeLog();
    }
}
