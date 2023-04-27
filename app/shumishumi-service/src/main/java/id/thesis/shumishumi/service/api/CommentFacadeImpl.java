package id.thesis.shumishumi.service.api;

import id.thesis.shumishumi.facade.api.CommentFacade;
import id.thesis.shumishumi.facade.request.comment.CreateCommentRequest;
import id.thesis.shumishumi.facade.result.comment.CreateCommentResult;
import org.springframework.stereotype.Service;

@Service
public class CommentFacadeImpl extends ProcessFacade implements CommentFacade {
    @Override
    public CreateCommentResult create(CreateCommentRequest request) {
        return null;
    }
}
