package id.thesis.shumishumi.core.request.post;

import id.thesis.shumishumi.core.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostDeleteRequest extends BaseRequest {
    private static final long serialVersionUID = 7800082969964841541L;

    private String postId;
}
