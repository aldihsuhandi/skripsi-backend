package id.thesis.shumishumi.facade.result.comment;

import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateCommentResult extends BaseResult {
    private static final long serialVersionUID = 6628080431549540015L;

    private String commentId;
}
