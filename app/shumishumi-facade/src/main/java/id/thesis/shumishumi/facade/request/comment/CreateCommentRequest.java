package id.thesis.shumishumi.facade.request.comment;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateCommentRequest extends BaseRequest {
    private static final long serialVersionUID = 8238502783991467003L;

    private String content;
    private String replyTo;
    private String replyId;
}
