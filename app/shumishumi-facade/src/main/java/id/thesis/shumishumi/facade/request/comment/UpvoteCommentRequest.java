package id.thesis.shumishumi.facade.request.comment;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpvoteCommentRequest extends BaseRequest {
    private static final long serialVersionUID = -96101294145421237L;

    private String commentId;
}
