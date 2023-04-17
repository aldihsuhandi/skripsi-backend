package id.thesis.shumishumi.core.request.post;

import id.thesis.shumishumi.core.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostUpvoteRequest extends BaseRequest {
    private static final long serialVersionUID = -1019876145347507684L;

    private String postId;
}
