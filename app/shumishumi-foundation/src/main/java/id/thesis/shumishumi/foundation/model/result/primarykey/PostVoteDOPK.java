package id.thesis.shumishumi.foundation.model.result.primarykey;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PostVoteDOPK implements Serializable {
    private static final long serialVersionUID = 1968817105032096898L;

    @Column(name = "post_id")
    private String postId;

    @Column(name = "user_id")
    private String userId;

    public PostVoteDOPK(String postId, String userId) {
        this.postId = postId;
        this.userId = userId;
    }

    public PostVoteDOPK() {
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
