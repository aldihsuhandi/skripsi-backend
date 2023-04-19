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
    BEGINNER("1", "BEGINNER"),
    INTERMEDIATE("1", "INTERMEDIATE"),
    ENTHUSIAST("1", "ENTHUSIAST"),

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
