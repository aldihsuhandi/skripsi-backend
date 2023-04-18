package id.thesis.shumishumi.facade.result.post;

import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostCreateResult extends BaseResult {
    private static final long serialVersionUID = 684760749143109577L;

    private String postId;
}
