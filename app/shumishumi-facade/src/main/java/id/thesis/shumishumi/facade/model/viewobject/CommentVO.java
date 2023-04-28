package id.thesis.shumishumi.facade.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentVO extends BaseVO {
    private static final long serialVersionUID = 7159372068939098865L;

    private String commentId;
    private String content;
    private UserVO commenter;
    private String replyId;
    private String replyType;
    private int upvote;
    private int downvote;
    private int currentUserVote = 0;
    private boolean isDeleted;
}
