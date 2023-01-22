package id.thesis.shumishumi.common.constant;

import java.io.Serializable;

public class DatabaseConst implements Serializable {
    private static final long serialVersionUID = 775139837861759190L;

    public static final String STATEMENT_UPDATE = "update";
    public static final String STATEMENT_DELETE = "delete";
    public static final String STATEMENT_INSERT = "insert";
    public static final String STATEMENT_SELECT = "select";
    public static final String DATABASE_SELECT_ALL = "*";
    public static final String DATABASE_SELECT_COUNT = "COUNT(*)";
    public static final String COMPARATOR_EQUAL = "=";
    public static final String COMPARATOR_LIKE = "LIKE";
    public static final String COMPARATOR_GREATER = ">";
    public static final String COMPARATOR_LESSER = "<";
    public static final String COMPARATOR_GREATER_EQUAL = ">=";
    public static final String COMPARATOR_LESSER_EQUAL = "<=";
    public static final String COMPARATOR_IN = "in";
    public static final String APPEND_OPERATOR_AND = "and";
    public static final String APPEND_OPERATOR_OR = "or";

    // table
    public static final String TABLE_SESSION = "sessions";
    public static final String TABLE_USER = "users";
    public static final String TABLE_ROLES = "user_roles";
    public static final String TABLE_CLIENT = "clients";
    public static final String TABLE_CONTENT = "contents";
    public static final String TABLE_OTPS = "otps";
    public static final String TABLE_ITEM = "items";
    public static final String TABLE_ITEM_IMAGES = "item_images";
    public static final String TABLE_ITEM_CATEGORIES = "item_categories";
    public static final String TABLE_HOBBIES = "hobbies";
    public static final String TABLE_INTEREST_LEVEL = "interest_level";
    public static final String TABLE_ACTIVITY = "activities";
    public static final String TABLE_USER_ACTIVITY = "user_activities";

    // General
    public static final String GMT_CREATE = "gmt_create";
    public static final String GMT_MODIFIED = "gmt_modified";
    public static final String IS_DELETED = "is_deleted";
    public static final String IS_ACTIVE = "is_active";
    public static final String MERCHANT_ID = "merchant_id";
    public static final String COUNT = "count";

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

    // item
    public static final String ITEM_ID = "item_id";
    public static final String ITEM_NAME = "item_name";
    public static final String ITEM_PRICE = "item_price";
    public static final String ITEM_DESCRIPTION = "item_description";
    public static final String ITEM_QUANTITY = "item_quantity";
    public static final String MERCHANT_LEVEL_ID = "merchant_level_id";
    public static final String USER_LEVEL_ID = "user_level_id";

    // item images
    public static final String ITEM_IMAGE_ID = "item_image_id";
    public static final String ITEM_IMAGE = "item_image";

    // item category
    public static final String CATEGORY_ID = "category_id";
    public static final String CATEGORY_NAME = "category_name";

    // Hobby
    public static final String HOBBY_ID = "hobby_id";
    public static final String HOBBY_NAME = "hobby_name";

    // Interest Level
    public static final String INTEREST_LEVEL_ID = "interest_level_id";
    public static final String INTEREST_LEVEL_NAME = "level_name";

    // Activity
    public static final String ACTIVITY_ID = "activity_id";
    public static final String ACTIVITY_NAME = "activity_name";
    public static final String ACTIVITY_POINT = "point";

    // User Activity
    public static final String USER_ACTIVITY_ID = "user_activity_id";

}
