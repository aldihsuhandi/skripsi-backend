package id.thesis.shumishumi.facade.model.enumeration;

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
    ITEM_NOT_ENOUGH("ITEM_NOT_ENOUGH", "item quantity is not enough"),
    ITEM_ALREADY_APPROVED("ITEM_ALREADY_APPROVED", "item already approved"),

    IMAGE_NOT_FOUND("IMAGE_NOT_FOUND", "cannot found the image you trying to download"),

    OAUTH_ERROR("OAUTH_ERROR", "API Authentication error"),

    POST_NOT_FOUND("POST_NOT_FOUND", "post not found"),

    COMMENT_NOT_FOUND("COMMENT_NOT_FOUND", "comment not found"),

    REQUEST_NOT_FOUND("REQUEST_NOT_FOUND", "request not found"),

    TRANSACTION_NOT_FOUND("TRANSACTION_NOT_FOUND", "transaction not found"),
    TRANSACTION_STATUS_INVALID("TRANSACTION_STATUS_INVALID", "transaction status is not valid"),

    REVIEW_NOT_FOUND("REVIEW_NOT_FOUND", "review not found"),

    CART_NOT_FOUND("CART_NOT_FOUND", "cart not found"),

    ;

    private final String errorCode;
    private final String errorMsg;

    ShumishumiErrorCodeEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }
}
