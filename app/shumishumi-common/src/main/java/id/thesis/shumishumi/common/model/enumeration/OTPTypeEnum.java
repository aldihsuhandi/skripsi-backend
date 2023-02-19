package id.thesis.shumishumi.common.model.enumeration;

import lombok.Getter;

@Getter
public enum OTPTypeEnum {
    USER_ACTIVATION("a2c82a3b-620e-442f-b3a5-9e55f068e68b", "USER_ACTIVATION"),
    FORGOT_PASSWORD("3af59f24-f0e0-4a28-8184-816a3d99820e", "FORGOT_PASSWORD");

    OTPTypeEnum(String id, String name) {
        this.id = id;
        this.name = name;
    }

    private final String id;

    private final String name;

    public static OTPTypeEnum findByName(String name) {
        for (OTPTypeEnum enums : values()) {
            if (enums.getName().equals(name)) {
                return enums;
            }
        }
        return null;
    }

    public static OTPTypeEnum findById(String id) {
        for (OTPTypeEnum enums : values()) {
            if (enums.getId().equals(id)) {
                return enums;
            }
        }
        return null;
    }
}
