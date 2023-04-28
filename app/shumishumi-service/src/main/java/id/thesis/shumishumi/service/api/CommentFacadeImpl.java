package id.thesis.shumishumi.service.api;

import id.thesis.shumishumi.core.callback.ProcessCallback;
import id.thesis.shumishumi.core.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.facade.api.CommentFacade;
import id.thesis.shumishumi.facade.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.facade.request.comment.CreateCommentRequest;
import id.thesis.shumishumi.facade.request.comment.DeleteCommentRequest;
import id.thesis.shumishumi.facade.request.comment.EditCommentRequest;
import id.thesis.shumishumi.facade.request.comment.QueryCommentRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.comment.CreateCommentResult;
import id.thesis.shumishumi.facade.result.comment.DeleteCommentResult;
import id.thesis.shumishumi.facade.result.comment.EditCommentResult;
import id.thesis.shumishumi.facade.result.comment.QueryCommentResult;
import org.springframework.stereotype.Service;

@Service
public class CommentFacadeImpl extends ProcessFacade implements CommentFacade {
    @Override
    public CreateCommentResult create(CreateCommentRequest request) {
        return (CreateCommentResult) ProcessCallbackSupport.process(ProcessTypeEnum.COMMENT_CREATE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new CreateCommentResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public EditCommentResult edit(EditCommentRequest request) {
        return (EditCommentResult) ProcessCallbackSupport.process(ProcessTypeEnum.COMMENT_EDIT, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new EditCommentResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public DeleteCommentResult delete(DeleteCommentRequest request) {
        return (DeleteCommentResult) ProcessCallbackSupport.process(ProcessTypeEnum.COMMENT_DELETE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new DeleteCommentResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public QueryCommentResult query(QueryCommentRequest request) {
        return (QueryCommentResult) ProcessCallbackSupport.process(ProcessTypeEnum.COMMENT_QUERY, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new QueryCommentResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }
}
