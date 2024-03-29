/**
 *
 */
package id.thesis.shumishumi.facade.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ActivityVO.java, v 0.1 2023‐01‐19 9:54 AM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class ActivityVO extends BaseVO {
    private static final long serialVersionUID = -3892697212166314629L;

    private String activityId;
    private String activityName;
    private Integer point;
}
