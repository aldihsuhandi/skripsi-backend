package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.comment.CreateCommentRequest;
import id.thesis.shumishumi.facade.request.comment.EditCommentRequest;
import id.thesis.shumishumi.facade.result.comment.CreateCommentResult;
import id.thesis.shumishumi.facade.result.comment.EditCommentResult;

public interface CommentFacade {
    CreateCommentResult create(CreateCommentRequest request);

    EditCommentResult edit(EditCommentRequest request);
}
