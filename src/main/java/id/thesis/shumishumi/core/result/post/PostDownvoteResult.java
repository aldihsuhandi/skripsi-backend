package id.thesis.shumishumi.core.result.post;

import id.thesis.shumishumi.core.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostDownvoteResult extends BaseResult {
    private static final long serialVersionUID = -3587913167208226798L;

    private int value;
}
