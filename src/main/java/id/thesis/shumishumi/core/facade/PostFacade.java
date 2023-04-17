package id.thesis.shumishumi.core.facade;

import id.thesis.shumishumi.core.request.post.PostCreateRequest;
import id.thesis.shumishumi.core.request.post.PostDeleteRequest;
import id.thesis.shumishumi.core.request.post.PostDownvoteRequest;
import id.thesis.shumishumi.core.request.post.PostEditRequest;
import id.thesis.shumishumi.core.request.post.PostQueryListRequest;
import id.thesis.shumishumi.core.request.post.PostQueryRequest;
import id.thesis.shumishumi.core.request.post.PostUpvoteRequest;
import id.thesis.shumishumi.core.result.post.PostCreateResult;
import id.thesis.shumishumi.core.result.post.PostDeleteResult;
import id.thesis.shumishumi.core.result.post.PostDownvoteResult;
import id.thesis.shumishumi.core.result.post.PostEditResult;
import id.thesis.shumishumi.core.result.post.PostQueryListResult;
import id.thesis.shumishumi.core.result.post.PostQueryResult;
import id.thesis.shumishumi.core.result.post.PostUpvoteResult;

public interface PostFacade {
    PostCreateResult create(PostCreateRequest request);

    PostEditResult edit(PostEditRequest request);

    PostDeleteResult delete(PostDeleteRequest request);

    PostUpvoteResult upvote(PostUpvoteRequest request);

    PostDownvoteResult downvote(PostDownvoteRequest request);

    PostQueryResult queryDetail(PostQueryRequest request);

    PostQueryListResult queryList(PostQueryListRequest request);
}
