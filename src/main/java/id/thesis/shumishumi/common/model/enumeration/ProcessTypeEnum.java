package id.thesis.shumishumi.common.model.enumeration;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public enum ProcessTypeEnum {
    USER_REGISTER("USER_REGISTER", "userRegisterValidator", "userRegisterProcessor", false);
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
