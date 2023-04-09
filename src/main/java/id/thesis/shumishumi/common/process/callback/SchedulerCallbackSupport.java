/**
 *
 */
package id.thesis.shumishumi.common.process.callback;

import id.thesis.shumishumi.common.util.constant.LogConstant;
import id.thesis.shumishumi.common.model.context.TracerContext;
import id.thesis.shumishumi.common.util.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: SchedulerCallbackSupport.java, v 0.1 2023‐01‐13 11:52 Aldih Suhandi Exp $$
 */
public class SchedulerCallbackSupport {

    private static final Logger LOGGER = LoggerFactory.
            getLogger(LogConstant.SCHEDULER_LOGGER);

    public static void process(final String schedulerName, final SchedulerCallback callback) {
        TracerContext.initialize();
        Date beginDate = new Date();

        LogUtil.info(LOGGER, schedulerName + " started");

        callback.executeTask();

        LogUtil.info(LOGGER, schedulerName + " finish");

        TracerContext.removeTracer();
    }
}
