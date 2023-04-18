package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.post.PostCreateRequest;
import id.thesis.shumishumi.facade.request.post.PostDeleteRequest;
import id.thesis.shumishumi.facade.request.post.PostDownvoteRequest;
import id.thesis.shumishumi.facade.request.post.PostEditRequest;
import id.thesis.shumishumi.facade.request.post.PostQueryListRequest;
import id.thesis.shumishumi.facade.request.post.PostQueryRequest;
import id.thesis.shumishumi.facade.request.post.PostUpvoteRequest;
import id.thesis.shumishumi.facade.result.post.PostCreateResult;
import id.thesis.shumishumi.facade.result.post.PostDeleteResult;
import id.thesis.shumishumi.facade.result.post.PostDownvoteResult;
import id.thesis.shumishumi.facade.result.post.PostEditResult;
import id.thesis.shumishumi.facade.result.post.PostQueryListResult;
import id.thesis.shumishumi.facade.result.post.PostQueryResult;
import id.thesis.shumishumi.facade.result.post.PostUpvoteResult;

public interface PostFacade {
    PostCreateResult create(PostCreateRequest request);

    PostEditResult edit(PostEditRequest request);

    PostDeleteResult delete(PostDeleteRequest request);

    PostUpvoteResult upvote(PostUpvoteRequest request);

    PostDownvoteResult downvote(PostDownvoteRequest request);

    PostQueryResult queryDetail(PostQueryRequest request);

    PostQueryListResult queryList(PostQueryListRequest request);
}
