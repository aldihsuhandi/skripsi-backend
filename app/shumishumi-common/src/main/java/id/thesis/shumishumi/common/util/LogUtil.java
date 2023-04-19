package id.thesis.shumishumi.common.util;

import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.context.TracerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogUtil {
    private static final Logger EXCEPTION_LOGGER = LoggerFactory.getLogger(LogConstant.EXCEPTION_LOGGER);

    public static void exception(String message, Exception e) {
        message = appendTraceId(message);
        EXCEPTION_LOGGER.error(message, e);
    }

    public static void info(Logger logger, String message, Object o) {
        message = appendTraceId(message);
        message = appendObject(message, o);

        logger.info(message);
    }

    public static void info(Logger logger, String message) {
        message = appendTraceId(message);

        logger.info(message);
    }

    public static void info(Logger logger, Object o) {
        String message = appendTraceId("");
        message += o.toString();

        logger.info(message);
    }

    private static String appendTraceId(String message) {
        return TracerContext.getTraceId() +
                " - " + message;
    }

    private static String appendObject(String message, Object o) {
        return message + ": " + o.toString();
    }
}
