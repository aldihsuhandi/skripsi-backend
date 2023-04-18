/**
 *
 */
package id.thesis.shumishumi.facade.model.enumeration;

import lombok.Getter;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ActivityEnum.java, v 0.1 2023‐01‐19 10:56 AM Aldih Suhandi Exp $$
 */
@Getter
public enum ActivityEnum {

    BUY("BUY"),
    LOOK("LOOK"),
    ;

    private String name;

    ActivityEnum(String name) {
        this.name = name;
    }
}
