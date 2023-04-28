package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.comment.CreateCommentRequest;
import id.thesis.shumishumi.facade.request.comment.DeleteCommentRequest;
import id.thesis.shumishumi.facade.request.comment.EditCommentRequest;
import id.thesis.shumishumi.facade.request.comment.QueryCommentRequest;
import id.thesis.shumishumi.facade.result.comment.CreateCommentResult;
import id.thesis.shumishumi.facade.result.comment.DeleteCommentResult;
import id.thesis.shumishumi.facade.result.comment.EditCommentResult;
import id.thesis.shumishumi.facade.result.comment.QueryCommentResult;

public interface CommentFacade {
    CreateCommentResult create(CreateCommentRequest request);

    EditCommentResult edit(EditCommentRequest request);

    DeleteCommentResult delete(DeleteCommentRequest request);

    QueryCommentResult query(QueryCommentRequest request);
}
