package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.comment.CreateCommentRequest;
import id.thesis.shumishumi.facade.request.comment.DeleteCommentRequest;
import id.thesis.shumishumi.facade.request.comment.DownvoteCommentRequest;
import id.thesis.shumishumi.facade.request.comment.EditCommentRequest;
import id.thesis.shumishumi.facade.request.comment.QueryCommentRequest;
import id.thesis.shumishumi.facade.request.comment.UpvoteCommentRequest;
import id.thesis.shumishumi.facade.result.comment.CreateCommentResult;
import id.thesis.shumishumi.facade.result.comment.DeleteCommentResult;
import id.thesis.shumishumi.facade.result.comment.DownvoteCommentResult;
import id.thesis.shumishumi.facade.result.comment.EditCommentResult;
import id.thesis.shumishumi.facade.result.comment.QueryCommentResult;
import id.thesis.shumishumi.facade.result.comment.UpvoteCommentResult;

public interface CommentFacade {
    CreateCommentResult create(CreateCommentRequest request);

    EditCommentResult edit(EditCommentRequest request);

    DeleteCommentResult delete(DeleteCommentRequest request);

    QueryCommentResult query(QueryCommentRequest request);

    UpvoteCommentResult upvote(UpvoteCommentRequest request);

    DownvoteCommentResult downvote(DownvoteCommentRequest request);
}
