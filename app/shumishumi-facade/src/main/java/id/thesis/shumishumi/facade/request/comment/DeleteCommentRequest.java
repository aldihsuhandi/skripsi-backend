package id.thesis.shumishumi.facade.request.comment;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class DeleteCommentRequest extends BaseRequest {
    private static final long serialVersionUID = 3510493922084830054L;

    private String commentId;
}
