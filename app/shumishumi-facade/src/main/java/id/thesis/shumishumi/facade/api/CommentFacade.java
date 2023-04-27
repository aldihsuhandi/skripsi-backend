package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.comment.CreateCommentRequest;
import id.thesis.shumishumi.facade.result.comment.CreateCommentResult;

public interface CommentFacade {
    CreateCommentResult create(CreateCommentRequest request);
}
