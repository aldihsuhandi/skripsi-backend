package id.thesis.shumishumi.facade.request.comment;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EditCommentRequest extends BaseRequest {
    private static final long serialVersionUID = 8660925856995116317L;

    private String commentId;
    private String content;
}
