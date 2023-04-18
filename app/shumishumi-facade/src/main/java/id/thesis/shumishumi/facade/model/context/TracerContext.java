package id.thesis.shumishumi.facade.model.context;

import id.thesis.shumishumi.facade.model.constant.CommonConst;

import java.util.UUID;

public class TracerContext {
    private static TracerContext instance;
    private final String traceId;

    private TracerContext(String traceId) {
        this.traceId = traceId;
    }

    private static String generateTraceId() {
        return CommonConst.TRACE_PREPEND +
                UUID.randomUUID().toString().replace("-", "")
                        .substring(0, 20);
    }

    public static void initialize() {
        if (instance == null) {
            synchronized (TracerContext.class) {
                if (instance == null) {
                    String traceId = generateTraceId();
                    instance = new TracerContext(traceId);
                }
            }
        }
    }

    public static String getTraceId() {
        if (instance == null) {
            synchronized (TracerContext.class) {
                if (instance == null) {
                    String traceId = generateTraceId();
                    instance = new TracerContext(traceId);
                }
            }
        }

        return instance.getterTraceId();
    }

    public static void removeTracer() {
        if (instance == null) {
            synchronized (TracerContext.class) {
                if (instance == null) {
                    String traceId = generateTraceId();
                    instance = new TracerContext(traceId);
                }
            }
        }
        instance = null;
    }

    private String getterTraceId() {
        return this.traceId;
    }
}
