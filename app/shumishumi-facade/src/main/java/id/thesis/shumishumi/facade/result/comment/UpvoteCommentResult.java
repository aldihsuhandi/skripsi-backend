package id.thesis.shumishumi.facade.result.comment;

import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpvoteCommentResult extends BaseResult {
    private static final long serialVersionUID = -86475655483081692L;

    private int value;
}
