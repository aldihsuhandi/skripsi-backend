/**
 *
 */
package id.thesis.shumishumi.foundation.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserActivityDO.java, v 0.1 2023‐01‐19 9:17 AM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class UserActivityDO extends BaseDO {
    private static final long serialVersionUID = 8676792820966855468L;

    private String userActivityId;
    private String userId;
    private String itemId;
    private String activityId;
}
