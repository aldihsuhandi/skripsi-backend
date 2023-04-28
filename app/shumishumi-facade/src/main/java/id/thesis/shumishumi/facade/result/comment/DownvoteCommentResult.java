package id.thesis.shumishumi.facade.result.comment;

import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DownvoteCommentResult extends BaseResult {
    private static final long serialVersionUID = -7132586977200527446L;

    private int value;
}
