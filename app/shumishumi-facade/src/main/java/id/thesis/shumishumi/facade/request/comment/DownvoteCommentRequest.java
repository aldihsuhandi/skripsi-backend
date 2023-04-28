package id.thesis.shumishumi.facade.request.comment;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DownvoteCommentRequest extends BaseRequest {
    private static final long serialVersionUID = -3291915143880101836L;

    private String commentId;
}
