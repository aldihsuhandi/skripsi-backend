package id.thesis.shumishumi.facade.result.post;

import id.thesis.shumishumi.common.model.context.PagingContext;
import id.thesis.shumishumi.common.model.summary.PostSummary;
import id.thesis.shumishumi.facade.result.BaseResult;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class PostQueryListResult extends BaseResult {
    private static final long serialVersionUID = -8047211385796122416L;

    private List<PostSummary> posts;
    private PagingContext pagingContext;
}
