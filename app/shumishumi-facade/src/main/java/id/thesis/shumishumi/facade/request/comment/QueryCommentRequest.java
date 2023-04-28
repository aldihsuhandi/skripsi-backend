package id.thesis.shumishumi.facade.request.comment;

import id.thesis.shumishumi.facade.request.BaseRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class QueryCommentRequest extends BaseRequest {
    private static final long serialVersionUID = 782144237869179663L;

    private String replyTo;
    private String replyId;
    private int pageNumber = 1;
    private int numberOfItem = 10;
}
