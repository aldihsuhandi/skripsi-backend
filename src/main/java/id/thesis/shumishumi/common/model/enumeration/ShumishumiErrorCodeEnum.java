package id.thesis.shumishumi.common.model.enumeration;

import lombok.Getter;

@Getter
public enum ShumishumiErrorCodeEnum {
    SYSTEM_ERROR("SYSTEM_ERROR", "System encountered an internal error"),
    PARAM_ILLEGAL("PARAM_ILLEGAL", "parameter not expected"),
    AUTHENTICATION_ERROR("AUTHENTICATION_ERROR", "authentication error"),
    USER_ALREADY_EXIST("USER_ALREADY_EXIST", "user already exist with a certain identification"),

    OAUTH_ERROR("OAUTH_ERROR", "API Authentication error");
    private final String errorCode;
    private final String errorMsg;

    ShumishumiErrorCodeEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
