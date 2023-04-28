package id.thesis.shumishumi.foundation.model.result.primarykey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CommentVoteDOPK implements Serializable {
    private static final long serialVersionUID = 4454755051085877442L;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "comment_id")
    private String commentId;

    public CommentVoteDOPK(String userId, String commentId) {
        this.userId = userId;
        this.commentId = commentId;
    }

    private CommentVoteDOPK() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }
}
