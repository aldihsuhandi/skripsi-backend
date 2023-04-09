package id.thesis.shumishumi.common.model.enumeration;

import lombok.Getter;

@Getter
public enum ShumishumiErrorCodeEnum {
    SUCCESS("SUCCESS", "success"),
    SYSTEM_ERROR("SYSTEM_ERROR", "System encountered an internal error"),
    PARAM_ILLEGAL("PARAM_ILLEGAL", "parameter not expected"),
    AUTHENTICATION_FAILED("AUTHENTICATION_FAILED", "authentication failed"),
    USER_ALREADY_EXIST("USER_ALREADY_EXIST", "user already exist with a certain identification"),
    USER_NOT_FOUND("USER_NOT_FOUND", "cannot find user by that identification"),
    USER_NOT_ACTIVE("USER_NOT_ACTIVE", "user is not active yet, please check your email"),
    USER_ROLE_INVALID("USER_ROLE_INVALID", "user role invalid"),
    USER_INVALID("USER_INVALID", "the current user is not valid"),

    SESSION_EXPIRED("SESSION_EXPIRED", "session expired"),

    OTP_NOT_EXIST("OTP_NOT_EXIST", "otp code not exist"),
    OTP_VALIDATION_ERROR("OTP_VALIDATION_ERROR", "otp validation error"),

    ITEM_NOT_FOUND("ITEM_NOT_FOUND", "item not found"),
    ITEM_ALREADY_APPROVED("ITEM_ALREADY_APPROVED", "item already approved"),

    IMAGE_NOT_FOUND("IMAGE_NOT_FOUND", "cannot found the image you trying to download"),

    OAUTH_ERROR("OAUTH_ERROR", "API Authentication error");
    private final String errorCode;
    private final String errorMsg;

    ShumishumiErrorCodeEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
