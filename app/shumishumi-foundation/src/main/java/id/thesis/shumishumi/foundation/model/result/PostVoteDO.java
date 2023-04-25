package id.thesis.shumishumi.foundation.model.result;

import id.thesis.shumishumi.foundation.model.result.primarykey.PostVoteDOPK;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "post_upvotes")
public class PostVoteDO extends BaseDO {
    @EmbeddedId
    private PostVoteDOPK pk;

    @Column(name = "value")
    private int value;

    public PostVoteDOPK getPk() {
        return pk;
    }

    public void setPk(PostVoteDOPK pk) {
        this.pk = pk;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
