package id.thesis.shumishumi.facade.result.post;

import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostUpvoteResult extends BaseResult {
    private static final long serialVersionUID = -8373176067206993202L;

    private int value;
}
