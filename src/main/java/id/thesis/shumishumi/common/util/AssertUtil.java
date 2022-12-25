package id.thesis.shumishumi.common.util;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;

public class AssertUtil {
    public static void isExpected(int value, int comp, ShumishumiErrorCodeEnum errorCode) throws ShumishumiException {
        if (value != comp) {
            throwError(errorCode.getErrorMsg(), errorCode);
        }
    }

    private static void throwError(String msg, ShumishumiErrorCodeEnum errorCode) throws ShumishumiException {
        throw new ShumishumiException(msg, errorCode);
    }
}
