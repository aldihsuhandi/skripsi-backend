/**
 *
 */
package id.thesis.shumishumi.facade.model.constant;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: CommonConst.java, v 0.1 2023‐01‐13 11:56 Aldih Suhandi Exp $$
 */
public class CommonConst {
    public static String EXPIRED_SESSION_SCHEDULER = "expired session scheduler";
    public static String EXPIRED_OTP_SCHEDULER = "expired otp scheduler";

    public static String SEPARATOR = "|";
    public static String SEPARATOR_SPLIT = "\\|";

    public static String MAIL_SMTP_AUTH = "mail.smtp.auth";
    public static String MAIL_SMTP_STARTTLS_ENABLE = "mail.smtp.starttls.enable";
    public static String MAIL_SMTP_HOST = "mail.smtp.host";
    public static String MAIL_SMTP_PORT = "mail.smtp.port";

    public static String LOCATION_CITY = "city";
    public static String LOCATION_PROVINCE = "province";
    public static String LOCATION_POST_CODE = "postCode";
    public static String LOCATION_DETAIL = "detail";

    public static String COMMENT_REPLY_POST = "POST";
    public static String COMMENT_REPLY_COMMENT = "COMMENT";

    public static String OTP_EMAIL_SUBJECT = "OTP_EMAIL_SUBJECT";
    public static String OTP_EMAIL = "OTP_EMAIL";
    public static String FORGOT_PASSWORD_EMAIL = "RESET_PASSWORD_EMAIL";
    public static String FORGOT_PASSWORD_EMAIL_SUBJECT = "RESET_PASSWORD_SUBJECT";
    public static String FORGOT_PASSWORD_URL_FORMAT = "RESET_PASSWORD_URL_FORMAT";

    public static String TRACE_PREPEND = "0a358";

    public static int INT_NOT_FOUND = -9123;

    public static String DICTIONARY_GENDER = "GENDER";
    public static String DICTIONARY_ITEM_SORTING = "ITEM_SORTING";
    public static String DICTIONARY_SORTING_TYPE = "SORTING_TYPE";

    /**
     * MIDTRANS
     */
    public static String MIDTRANS_TRANSACTION_DETAIL = "transaction_details";
    public static String MIDTRANS_ITEM_DETAILS = "item_details";
    public static String MIDTRANS_PAYMENT_TYPE = "payment_type";
    public static String MIDTRANS_ORDER_ID = "order_id";
    public static String MIDTRANS_GROSS_AMOUNT = "gross_amount";
    public static String MIDTRANS_BANK_TRANSFER = "bank_transfer";
    public static String MIDTRANS_BANK = "bank";

    /**
     * Extend Info Key
     */
    public static String EXTEND_INFO_WHATSAPP = "canWhatsapp";
    public static String EXTEND_INFO_TELEGRAM = "canTelegram";
}
