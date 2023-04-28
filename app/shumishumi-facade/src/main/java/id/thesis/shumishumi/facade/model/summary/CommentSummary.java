package id.thesis.shumishumi.facade.model.summary;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CommentSummary extends BaseSummary {
    private static final long serialVersionUID = 4034284307923184336L;

    private String commentId;
    private String content;
    private UserSummary commenter;
    private int upvote;
    private int downvote;
    private int currentUserVote;
}
