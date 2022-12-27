package id.thesis.shumishumi.common.model.enumeration;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ProcessTypeEnum {
    USER_REGISTER("USER_REGISTER", "userRegisterValidator", "userRegisterProcessor", false),
    USER_LOGIN("USER_LOGIN", "userLoginValidator", "userLoginProcessor", false),
    USER_UPDATE("USER_UPDATE", "userUpdateValidator", "userLoginProcessor", true),

    SESSION_LOGOUT("SESSION_LOGOUT", "sessionLogoutValidator", "sessionLogoutProcessor", true),

    CLIENT_AUTH("CLIENT_AUTH", "clientAuthValidator", "clientAuthProcessor", false);
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
