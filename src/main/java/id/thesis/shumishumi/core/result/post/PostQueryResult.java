package id.thesis.shumishumi.core.result.post;

import id.thesis.shumishumi.common.model.summary.PostSummary;
import id.thesis.shumishumi.core.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostQueryResult extends BaseResult {
    private static final long serialVersionUID = -5357615856391853890L;

    private PostSummary post;
}
