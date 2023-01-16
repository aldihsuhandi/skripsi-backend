package id.thesis.shumishumi.common.constant;

import java.io.Serializable;

public class DatabaseConst implements Serializable {
    private static final long serialVersionUID = 775139837861759190L;

    public static String STATEMENT_UPDATE = "update";
    public static String STATEMENT_DELETE = "delete";
    public static String STATEMENT_INSERT = "insert";
    public static String STATEMENT_SELECT = "select";
    public static String DATABASE_SELECT_ALL = "*";
    public static String COMPARATOR_EQUAL = "=";
    public static String COMPARATOR_GREATER = ">";
    public static String COMPARATOR_LESSER = "<";
    public static String COMPARATOR_GREATER_EQUAL = ">=";
    public static String COMPARATOR_LESSER_EQUAL = "<=";
    public static String COMPARATOR_IN = "in";
    public static String APPEND_OPERATOR_AND = "and";
    public static String APPEND_OPERATOR_OR = "or";

    public static String TABLE_SESSION = "sessions";
    public static String TABLE_USER = "users";
    public static String TABLE_ROLES = "user_roles";
    public static String TABLE_CLIENT = "clients";
    public static String TABLE_CONTENT = "contents";
    public static String TABLE_OTPS = "otps";

    // General
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    public static final String IS_DELETED = "is_deleted";
    public static final String IS_ACTIVE = "is_active";

    // User
    public static final String USER_ID = "user_id";
    public static final String EMAIL = "email";
    public static final String USERNAME = "username";
    public static final String PHONE_NUMBER = "phone_number";
    public static final String PROFILE_PICTURE = "profile_picture";
    public static final String PASSWORD = "password";

    // Role
    public static final String ROLE_ID = "role_id";
    public static final String ROLE_NAME = "role_name";

    // Session
    public static final String SESSION_ID = "session_id";
    public static final String IS_REMEMBERED = "is_remembered";
    public static final String SESSION_DT = "session_dt";

    // client
    public static final String CLIENT_ID = "client_id";
    public static final String CLIENT_NAME = "client_name";
    public static final String CLIENT_SECRET = "client_secret";

    // content
    public static final String CONTENT_NAME = "content_name";
    public static final String CONTENT = "content";

    // otp
    public static final String OTP_ID = "otp_id";
    public static final String OTP = "otp";
    public static final String OTP_DT = "otp_dt";
    public static final String OTP_TYPE_ID = "type_id";
}
