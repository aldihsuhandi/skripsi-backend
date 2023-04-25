/**
 *
 */
package id.thesis.shumishumi.facade.model.enumeration;

import lombok.Getter;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: InterestLevelEnum.java, v 0.1 2023‐01‐16 4:34 PM Aldih Suhandi Exp $$
 */
@Getter
public enum InterestLevelEnum {
    BEGINNER("da61603a-3252-4f7d-bf07-97ef5ef168cc", "BEGINNER"),
    INTERMEDIATE("ebb5d871-af7d-4b59-807c-bef5f8962e3c", "INTERMEDIATE"),
    ENTHUSIAST("5684822d-ab2d-4ed6-a124-c3754035d80c", "ENTHUSIAST"),

    ;
    private final String id;
    private final String level;

    InterestLevelEnum(String id, String level) {
        this.id = id;
        this.level = level;
    }

    public static InterestLevelEnum findByName(String name) {
        for (InterestLevelEnum interestLevelEnum : values()) {
            if (interestLevelEnum.getLevel().equals(name)) {
                return interestLevelEnum;
            }
        }
        return null;
    }
}
