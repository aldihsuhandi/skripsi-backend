package id.thesis.shumishumi.foundation.model.result;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "interest_level")
public class InterestLevelDO extends BaseDO {
    private static final long serialVersionUID = 203728092658437272L;

    @Id
    @Column(name = "interest_level_id")
    private String interestLevelId;

    @Column(name = "level_name")
    private String interestLevelName;

    public String getInterestLevelId() {
        return interestLevelId;
    }

    public void setInterestLevelId(String interestLevelId) {
        this.interestLevelId = interestLevelId;
    }

    public String getInterestLevelName() {
        return interestLevelName;
    }

    public void setInterestLevelName(String interestLevelName) {
        this.interestLevelName = interestLevelName;
    }
}
