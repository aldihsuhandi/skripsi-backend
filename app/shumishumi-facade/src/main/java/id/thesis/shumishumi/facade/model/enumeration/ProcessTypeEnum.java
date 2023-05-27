package id.thesis.shumishumi.facade.model.enumeration;

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

    MERCHANT_APPLY("MERCHANT_APPLY", "merchantApplyValidator", "merchantApplyProcessor", true),

    RESET_PASSWORD("RESET_PASSWORD", "resetPasswordValidator", "resetPasswordProcessor", false),
    FORGOT_PASSWORD("FORGOT_PASSWORD", "forgotPasswordValidator", "forgotPasswordProcessor", false),
    FORGOT_PASSWORD_QUERY("FORGOT_PASSWORD_QUERY", "forgotPasswordQueryValidator", "forgotPasswordQueryProcessor", false),

    EMAIL_ENCRYPT("EMAIL_ENCRYPT", "emailEncryptValidator", "emailEncryptProcessor", false),
    EMAIL_DECRYPT("EMAIL_DECRYPT", "emailDecryptValidator", "emailDecryptProcessor", false),

    SESSION_QUERY("SESSION_QUERY", "sessionQueryValidator", "sessionQueryProcessor", true),
    SESSION_LOGOUT("SESSION_LOGOUT", "sessionLogoutValidator", "sessionLogoutProcessor", true),

    CLIENT_AUTH("CLIENT_AUTH", "clientAuthValidator", "clientAuthProcessor", false),

    OTP_SEND("OTP_SEND", "otpSendValidator", "otpSendProcessor", false),
    OTP_VALIDATE("OTP_VALIDATE", "otpValidateValidator", "otpValidateProcessor", false),

    ITEM_CREATE("ITEM_CREATE", "createItemValidator", "createItemProcessor", true),
    ITEM_QUERY("ITEM_QUERY", "queryItemValidator", "queryItemProcessor", false),
    ITEM_QUERY_DETAIL("ITEM_QUERY_DETAIL", "queryItemDetailValidator", "queryItemDetailProcessor", false),
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

    COMMENT_CREATE("COMMENT_CREATE", "commentCreateValidator", "commentCreateProcessor", true),
    COMMENT_EDIT("COMMENT_EDIT", "commentEditValidator", "commentEditProcessor", true),
    COMMENT_DELETE("COMMENT_DELETE", "commentDeleteValidator", "commentDeleteProcessor", true),
    COMMENT_QUERY("COMMENT_QUERY", "commentQueryValidator", "commentQueryProcessor", false),
    COMMENT_UPVOTE("COMMENT_UPVOTE", "commentUpvoteValidator", "commentUpvoteProcessor", true),
    COMMENT_DOWNVOTE("COMMENT_DOWNVOTE", "commentDownvoteValidator", "commentDownvoteProcessor", true),

    DICTIONARY_QUERY("DICTIONARY_QUERY", "dictionaryQueryValidator", "dictionaryQueryProcessor", false),

    CART_ADD("CART_ADD", "cartAddValidator", "cartAddProcessor", true),
    CART_UPDATE("CART_UPDATE", "cartUpdateValidator", "cartUpdateProcessor", true),
    CART_QUERY("CART_QUERY", "cartQueryValidator", "cartQueryProcessor", true),
    CART_CALCULATE("CART_CALCULATE", "cartCalculateValidator", "cartCalculateProcessor", true),

    TRANSACTION_CREATE("TRANSACTION_CREATE", "transactionCreateValidator", "transactionCreateProcessor", true),
    TRANSACTION_PAYMENT("TRANSACTION_PAYMENT", "transactionPaymentValidator", "transactionPaymentProcessor", true),
    TRANSACTION_DETAIL("TRANSACTION_DETAIL", "transactionQueryDetailValidator", "transactionQueryDetailProcessor", true),
    TRANSACTION_LIST("TRANSACTION_LIST", "transactionQueryValidator", "transactionQueryProcessor", true),

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
