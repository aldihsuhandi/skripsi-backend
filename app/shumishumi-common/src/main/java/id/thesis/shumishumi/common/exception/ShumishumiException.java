package id.thesis.shumishumi.common.exception;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShumishumiException extends RuntimeException {
    private ShumishumiErrorCodeEnum errorCode;

    public ShumishumiException(String message, ShumishumiErrorCodeEnum errorCodeEnum) {
        super(message);
        this.errorCode = errorCodeEnum;
    }
}
