package id.thesis.shumishumi.common.model.enumeration;

import lombok.Getter;

@Getter
public enum ProcessTypeEnum {
    ;
    private final String processName;
    private final String processorName;
    private final String validatorName;

    ProcessTypeEnum(String processName, String processorName, String validatorName) {
        this.processName = processName;
        this.processorName = processorName;
        this.validatorName = validatorName;
    }
}
