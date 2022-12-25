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
    public static String COMPARATOR_GREATER_THAN = ">=";
    public static String COMPARATOR_LESSER_THAN = "<=";
    public static String COMPARATOR_IN = "in";
    public static String APPEND_OPERATOR_AND = "and";
    public static String APPEND_OPERATOR_OR = "or";

    public static String TABLE_SESSION = "sessions";

    // General
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";

    // User
    public static final String USER_ID = "user_id";

    // Session
    public static String SESSION_ID = "session_id";
    public static final String IS_ACTIVE = "is_active";
    public static final String IS_REMEMBERED = "is_remembered";
    public static final String SESSION_DT = "session_dt";
}
