package id.thesis.shumishumi.common.util;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;

public class ParamChecker {
    public static void isNotNull(Object value, String param, ShumishumiErrorCodeEnum errorCode) throws ShumishumiException {
        if (value == null) {
            throwsException(String.format("%s cannot be null", param), errorCode);
        }
    }

    public static void isNotEmpty(String value, String param, ShumishumiErrorCodeEnum errorCode) throws ShumishumiException {
        if (value == null || value.isEmpty()) {
            throwsException(String.format("%s cannot be empty", param), errorCode);
        }
    }

    public static void isExpected(boolean value, String param, ShumishumiErrorCodeEnum errorCode) throws ShumishumiException {
        if (!value) {
            throwsException(String.format("the value of %s is not expected", param), errorCode);
        }
    }

    public static void isExpected(String value, String comp, String param, ShumishumiErrorCodeEnum errorCode) throws ShumishumiException {
        if (!value.matches(comp)) {
            throwsException(String.format("the value of %s is not expected", param), errorCode);
        }
    }

    private static void throwsException(String msg, ShumishumiErrorCodeEnum errorCode) throws ShumishumiException {
        throw new ShumishumiException(msg, errorCode);
    }
}
