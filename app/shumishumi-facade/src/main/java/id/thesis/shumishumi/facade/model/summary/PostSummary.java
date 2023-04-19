package id.thesis.shumishumi.facade.model.summary;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PostSummary extends BaseSummary {
    private static final long serialVersionUID = 2411258490087889363L;

    private String postId;
    private String title;
    private String content;
    private List<String> tags;
    private List<String> images;
    private UserSummary userInfo;
    private int upvote;
    private int downvote;
    private int currentUserVote;
}
