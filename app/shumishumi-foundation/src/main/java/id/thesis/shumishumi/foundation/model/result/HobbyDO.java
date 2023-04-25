/**
 *
 */
package id.thesis.shumishumi.foundation.model.result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: HobbyDO.java, v 0.1 2023‐01‐17 13:09 Aldih Suhandi Exp $$
 */

@Entity
@Table(name = "hobbies")
public class HobbyDO extends BaseDO {
    private static final long serialVersionUID = -147906779250897196L;

    @Id
    @Column(name = "hobby_id")
    private String hobbyId;

    @Column(name = "hobby_name")
    private String hobbyName;

    public String getHobbyId() {
        return hobbyId;
    }

    public void setHobbyId(String hobbyId) {
        this.hobbyId = hobbyId;
    }

    public String getHobbyName() {
        return hobbyName;
    }

    public void setHobbyName(String hobbyName) {
        this.hobbyName = hobbyName;
    }
}
