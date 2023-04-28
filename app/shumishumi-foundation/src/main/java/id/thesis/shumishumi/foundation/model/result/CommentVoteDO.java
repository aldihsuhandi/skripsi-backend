package id.thesis.shumishumi.foundation.model.result;

import id.thesis.shumishumi.foundation.model.result.primarykey.CommentVoteDOPK;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "comment_upvotes")
public class CommentVoteDO extends BaseDO {
    @EmbeddedId
    private CommentVoteDOPK pk;

    @Column(name = "value")
    private int value;

    public CommentVoteDOPK getPk() {
        return pk;
    }

    public void setPk(CommentVoteDOPK pk) {
        this.pk = pk;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
