package id.thesis.shumishumi.facade.exception;

import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
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
