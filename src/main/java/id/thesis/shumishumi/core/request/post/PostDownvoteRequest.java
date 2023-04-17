package id.thesis.shumishumi.core.request.post;

import id.thesis.shumishumi.core.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostDownvoteRequest extends BaseRequest {
    private static final long serialVersionUID = 796471634735302724L;

    private String postId;
}
