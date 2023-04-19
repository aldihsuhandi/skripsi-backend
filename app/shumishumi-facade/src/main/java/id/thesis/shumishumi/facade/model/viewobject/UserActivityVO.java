/**
 *
 */
package id.thesis.shumishumi.facade.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserActivityVO.java, v 0.1 2023‐01‐19 9:54 AM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class UserActivityVO extends BaseVO {
    private static final long serialVersionUID = 7339838670364593214L;

    private String userActivityId;
    private String userId;
    private String itemId;
    private ActivityVO activityInfo;
}
