package id.thesis.shumishumi.web.controller;

import id.thesis.shumishumi.common.model.form.comment.CommentCreateForm;
import id.thesis.shumishumi.common.model.form.comment.CommentDeleteForm;
import id.thesis.shumishumi.common.model.form.comment.CommentDownvoteForm;
import id.thesis.shumishumi.common.model.form.comment.CommentEditForm;
import id.thesis.shumishumi.common.model.form.comment.CommentQueryForm;
import id.thesis.shumishumi.common.model.form.comment.CommentUpvoteForm;
import id.thesis.shumishumi.core.callback.ControllerCallback;
import id.thesis.shumishumi.core.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.facade.api.CommentFacade;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class CommentController extends BaseController {
    @Autowired
    private CommentFacade commentFacade;

    @PostMapping("/create")
    public ResponseEntity<CreateCommentResult> create(@RequestHeader HttpHeaders headers, @RequestBody CommentCreateForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<CreateCommentResult, CreateCommentRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public CreateCommentRequest composeRequest() {
                CreateCommentRequest request = new CreateCommentRequest();
                request.setReplyId(form.getReplyId());
                request.setReplyTo(form.getReplyTo());
                request.setContent(form.getContent());

                return request;
            }

            @Override
            public CreateCommentResult doProcess(CreateCommentRequest request) {
                return commentFacade.create(request);
            }
        });
    }

    @PostMapping("/edit")
    public ResponseEntity<EditCommentResult> edit(@RequestHeader HttpHeaders headers, @RequestBody CommentEditForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<EditCommentResult, EditCommentRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public EditCommentRequest composeRequest() {
                EditCommentRequest request = new EditCommentRequest();
                request.setCommentId(form.getCommentId());
                request.setContent(form.getContent());

                return request;
            }

            @Override
            public EditCommentResult doProcess(EditCommentRequest request) {
                return commentFacade.edit(request);
            }
        });
    }

    @PostMapping("/delete")
    public ResponseEntity<DeleteCommentResult> delete(@RequestHeader HttpHeaders headers, @RequestBody CommentDeleteForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<DeleteCommentResult, DeleteCommentRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public DeleteCommentRequest composeRequest() {
                DeleteCommentRequest request = new DeleteCommentRequest();
                request.setCommentId(form.getCommentId());

                return request;
            }

            @Override
            public DeleteCommentResult doProcess(DeleteCommentRequest request) {
                return commentFacade.delete(request);
            }
        });
    }

    @PostMapping("/upvote")
    public ResponseEntity<UpvoteCommentResult> upvote(@RequestHeader HttpHeaders headers, @RequestBody CommentUpvoteForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<UpvoteCommentResult, UpvoteCommentRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public UpvoteCommentRequest composeRequest() {
                UpvoteCommentRequest request = new UpvoteCommentRequest();
                request.setCommentId(form.getCommentId());

                return request;
            }

            @Override
            public UpvoteCommentResult doProcess(UpvoteCommentRequest request) {
                return commentFacade.upvote(request);
            }
        });
    }

    @PostMapping("/downvote")
    public ResponseEntity<DownvoteCommentResult> downvote(@RequestHeader HttpHeaders headers, @RequestBody CommentDownvoteForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<DownvoteCommentResult, DownvoteCommentRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public DownvoteCommentRequest composeRequest() {
                DownvoteCommentRequest request = new DownvoteCommentRequest();
                request.setCommentId(form.getCommentId());

                return request;
            }

            @Override
            public DownvoteCommentResult doProcess(DownvoteCommentRequest request) {
                return commentFacade.downvote(request);
            }
        });
    }

    @PostMapping("/query")
    public ResponseEntity<QueryCommentResult> query(@RequestHeader HttpHeaders headers, @RequestBody CommentQueryForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<QueryCommentResult, QueryCommentRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public QueryCommentRequest composeRequest() {
                QueryCommentRequest request = new QueryCommentRequest();
                request.setReplyId(form.getReplyId());
                request.setReplyTo(form.getReplyTo());
                request.setPageNumber(form.getPageNumber());
                request.setNumberOfItem(form.getNumberOfItem());

                return request;
            }

            @Override
            public QueryCommentResult doProcess(QueryCommentRequest request) {
                return commentFacade.query(request);
            }
        });
    }
}
