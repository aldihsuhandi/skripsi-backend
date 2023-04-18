package id.thesis.shumishumi.facade.model.viewobject;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PostVO extends BaseVO {
    private static final long serialVersionUID = -5957270056038506994L;

    private String postId;
    private String userId;
    private String title;
    private String content;
    private List<String> tags;
    private List<String> images;
    private int upvote;
    private int downvote;
    private int currentUserVote;
}
