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
    BEGINNER("1", "BEGINNER", 1),
    INTERMEDIATE("3", "INTERMEDIATE", 3),
    ENTHUSIAST("5", "ENTHUSIAST", 5),

    ;
    private final String id;
    private final String level;
    private final int index;

    InterestLevelEnum(String id, String level, int index) {
        this.id = id;
        this.level = level;
        this.index = index;
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
