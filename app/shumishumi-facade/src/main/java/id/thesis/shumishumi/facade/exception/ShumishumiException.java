package id.thesis.shumishumi.facade.exception;

import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ShumishumiException extends RuntimeException {
    private ShumishumiErrorCodeEnum errorCode;

    public ShumishumiException(Throwable cause, ShumishumiErrorCodeEnum errorCodeEnum) {
        super(cause.getMessage(), cause);
        this.errorCode = errorCodeEnum;
    }

    public ShumishumiException(String message, ShumishumiErrorCodeEnum errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
