package id.thesis.shumishumi.facade.request.post;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostQueryRequest extends BaseRequest {
    private static final long serialVersionUID = -309112631581443686L;

    private String postId;
}
