package id.thesis.shumishumi.common.model.enumeration;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ProcessTypeEnum {
    USER_REGISTER("USER_REGISTER", "userRegisterValidator", "userRegisterProcessor", false),
    USER_LOGIN("USER_LOGIN", "userLoginValidator", "userLoginProcessor", false),
    USER_UPDATE("USER_UPDATE", "userUpdateValidator", "userUpdateProcessor", true),
    USER_QUERY("USER_QUERY", "userQueryValidator", "userQueryProcessor", false),
    USER_ACTIVATE("USER_ACTIVATE", "userActivateValidator", "userActivateProcessor", false),
    FORGOT_PASSWORD("FORGOT_PASSWORD", "userForgotPasswordValidator", "userForgotPasswordProcessor", false),

    SESSION_QUERY("SESSION_QUERY", "sessionQueryValidator", "sessionQueryProcessor", true),
    SESSION_LOGOUT("SESSION_LOGOUT", "sessionLogoutValidator", "sessionLogoutProcessor", true),

    CLIENT_AUTH("CLIENT_AUTH", "clientAuthValidator", "clientAuthProcessor", false),

    OTP_SEND("OTP_SEND", "otpSendValidator", "otpSendProcessor", false),
    OTP_VALIDATE("OTP_VALIDATE", "otpValidateValidator", "otpValidateProcessor", false),

    ITEM_CREATE("ITEM_CREATE", "itemCreateValidator", "itemCreateProcessor", true),

    ITEM_QUERY("ITEM_QUERY", "itemQueryValidator", "itemQueryProcessor", false),

    ;

    private final String processName;
    private final String validatorName;
    private final String processorName;
    private final boolean needAuthentication;

    ProcessTypeEnum(String processName, String validatorName, String processorName, boolean needAuthentication) {
        this.processName = processName;
        this.validatorName = validatorName;
        this.processorName = processorName;
        this.needAuthentication = needAuthentication;
    }
}
