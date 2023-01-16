package id.thesis.shumishumi.test.util;

public class ResultAssert {
    public static void isSuccess(boolean bool) {
        if (!bool) {
            throw new RuntimeException();
        }
    }

    public static void isNotSuccess(boolean bool) {
        isSuccess(!bool);
    }

    public static void isExpected(String errorCode, String matchErrorCode) {
        if (!errorCode.equals(matchErrorCode)) {
            throw new RuntimeException(String.format("expected resultCode [%s], but got [%s]", matchErrorCode, errorCode));
        }
    }
}
