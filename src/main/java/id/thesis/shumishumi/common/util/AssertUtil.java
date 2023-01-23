package id.thesis.shumishumi.common.util;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;

public class AssertUtil {
    public static void isExpected(int value, int comp, ShumishumiErrorCodeEnum errorCode) {
        if (value != comp) {
            throwError(errorCode.getErrorMsg(), errorCode);
        }
    }

    public static void isExpected(Object value, Object comp, String msg, ShumishumiErrorCodeEnum errorCode) {
        if (!value.equals(comp)) {
            throwError(msg, errorCode);
        }
    }

    public static void isExpected(boolean val, String msg, ShumishumiErrorCodeEnum errorCodeEnum) {
        if (!val) {
            throwError(msg, errorCodeEnum);
        }
    }

    public static void isNull(Object o, String msg, ShumishumiErrorCodeEnum errorCode) {
        if (o != null) {
            throwError(msg, errorCode);
        }
    }

    public static void isNotNull(Object o, String msg, ShumishumiErrorCodeEnum errorCodeEnum) {
        if (o == null) {
            throwError(msg, errorCodeEnum);
        }
    }

    private static void throwError(String msg, ShumishumiErrorCodeEnum errorCode) {
        throw new ShumishumiException(msg, errorCode);
    }
}
