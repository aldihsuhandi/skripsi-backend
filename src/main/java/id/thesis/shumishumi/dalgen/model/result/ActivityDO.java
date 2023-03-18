/**
 * 
 *
 */
package id.thesis.shumishumi.dalgen.model.result;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: ActivityDO.java, v 0.1 2023‐01‐19 9:18 AM Aldih Suhandi Exp $$
 */
@Getter
@Setter
@ToString
public class ActivityDO extends BaseDO {
    private static final long serialVersionUID = -8936664416020994573L;

    private String activityId;
    private String activityName;
    private Integer point;
}
