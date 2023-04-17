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

    ITEM_CREATE("ITEM_CREATE", "createItemValidator", "createItemProcessor", true),
    ITEM_QUERY("ITEM_QUERY", "queryItemValidator", "queryItemProcessor", false),
    ITEM_UPDATE("ITEM_UPDATE", "updateItemValidator", "updateItemProcessor", true),
    ITEM_APPROVAL("ITEM_APPROVAL", "itemApprovalValidator", "itemApprovalProcessor", true),
    RECOMMEND("RECOMMEND", "recommendValidator", "recommendProcessor", false),
    AUTOCOMPLETE("ITEM_AUTOCOMPLETE", "autocompleteItemValidator", "autocompleteItemProcessor", false),

    ITEM_IMAGE_ADD("ITEM_IMAGE_ADD", "itemAddImageValidator", "itemAddImageProcessor", true),
    ITEM_IMAGE_REMOVE("ITEM_IMAGE_REMOVE", "itemRemoveImageValidator", "itemRemoveImageProcessor", true),

    WISHLIST_ADD("WISHLIST_ADD", "wishlistAddValidator", "wishlistAddProcessor", true),
    WISHLIST_REMOVE("WISHLIST_REMOVE", "wishlistRemoveValidator", "wishlistRemoveProcessor", true),
    WISHLIST_QUERY("WISHLIST_QUERY", "wishlistQueryValidator", "wishlistQueryProcessor", true),

    IMAGE_DOWNLOAD("IMAGE_DOWNLOAD", "imageDownloadValidator", "imageDownloadProcessor", false),
    IMAGE_UPLOAD("IMAGE_UPLOAD", "imageUploadValidator", "imageUploadProcessor", false),

    POST_CREATE("POST_CREATE", "postCreateValidator", "postCreateProcessor", true),
    POST_DELETE("POST_DELETE", "postDeleteValidator", "postDeleteProcessor", true),
    POST_EDIT("POST_EDIT", "postEditValidator", "postEditProcessor", true),
    POST_UPVOTE("POST_UPVOTE", "postUpvoteValidator", "postUpvoteProcessor", true),
    POST_DOWNVOTE("POST_DOWNVOTE", "postDownvoteValidator", "postDownvoteProcessor", true),
    POST_DETAIL_QUERY("POST_DETAIL_QUERY", "postQueryValidator", "postQueryProcessor", false),
    POST_QUERY("POST_QUERY", "postQueryListValidator", "postQueryListProcessor", false),

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
