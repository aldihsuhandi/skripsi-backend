package id.thesis.shumishumi.rest.controller;

import id.thesis.shumishumi.common.process.callback.ControllerCallback;
import id.thesis.shumishumi.common.process.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.core.facade.PostFacade;
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
import id.thesis.shumishumi.rest.form.post.PostCreateForm;
import id.thesis.shumishumi.rest.form.post.PostDeleteForm;
import id.thesis.shumishumi.rest.form.post.PostDownvoteForm;
import id.thesis.shumishumi.rest.form.post.PostEditForm;
import id.thesis.shumishumi.rest.form.post.PostQueryForm;
import id.thesis.shumishumi.rest.form.post.PostQueryListForm;
import id.thesis.shumishumi.rest.form.post.PostUpvoteForm;
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
@RequestMapping("/post")
public class PostController extends BaseController {
    @Autowired
    private PostFacade postFacade;

    @PostMapping("/create")
    public ResponseEntity<PostCreateResult> create(@RequestHeader HttpHeaders headers, @RequestBody PostCreateForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<PostCreateResult, PostCreateRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public PostCreateRequest composeRequest() {
                PostCreateRequest request = new PostCreateRequest();
                request.setTitle(form.getTitle());
                request.setContent(form.getContent());
                request.setTags(form.getTags());
                request.setImages(form.getImages());

                return request;
            }

            @Override
            public PostCreateResult doProcess(PostCreateRequest request) {
                return postFacade.create(request);
            }
        });
    }

    @PostMapping("/edit")
    public ResponseEntity<PostEditResult> edit(@RequestHeader HttpHeaders headers, @RequestBody PostEditForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<PostEditResult, PostEditRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public PostEditRequest composeRequest() {
                PostEditRequest request = new PostEditRequest();
                request.setPostId(form.getPostId());
                request.setTitle(form.getTitle());
                request.setContent(form.getContent());
                request.setTags(form.getTags());
                request.setImages(form.getImages());

                return request;
            }

            @Override
            public PostEditResult doProcess(PostEditRequest request) {
                return postFacade.edit(request);
            }
        });
    }

    @PostMapping("/delete")
    public ResponseEntity<PostDeleteResult> delete(@RequestHeader HttpHeaders headers, @RequestBody PostDeleteForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<PostDeleteResult, PostDeleteRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public PostDeleteRequest composeRequest() {
                PostDeleteRequest request = new PostDeleteRequest();
                request.setPostId(form.getPostId());

                return request;
            }

            @Override
            public PostDeleteResult doProcess(PostDeleteRequest request) {
                return postFacade.delete(request);
            }
        });
    }

    @PostMapping("/upvote")
    public ResponseEntity<PostUpvoteResult> upvote(@RequestHeader HttpHeaders headers, @RequestBody PostUpvoteForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<PostUpvoteResult, PostUpvoteRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public PostUpvoteRequest composeRequest() {
                PostUpvoteRequest request = new PostUpvoteRequest();
                request.setPostId(form.getPostId());

                return request;
            }

            @Override
            public PostUpvoteResult doProcess(PostUpvoteRequest request) {
                return postFacade.upvote(request);
            }
        });
    }

    @PostMapping("/downvote")
    public ResponseEntity<PostDownvoteResult> downvote(@RequestHeader HttpHeaders headers, @RequestBody PostDownvoteForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<PostDownvoteResult, PostDownvoteRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public PostDownvoteRequest composeRequest() {
                PostDownvoteRequest request = new PostDownvoteRequest();
                request.setPostId(form.getPostId());

                return request;
            }

            @Override
            public PostDownvoteResult doProcess(PostDownvoteRequest request) {
                return postFacade.downvote(request);
            }
        });
    }

    @PostMapping("/queryById")
    public ResponseEntity<PostQueryResult> query(@RequestHeader HttpHeaders headers, @RequestBody PostQueryForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<PostQueryResult, PostQueryRequest>() {

            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public PostQueryRequest composeRequest() {
                PostQueryRequest request = new PostQueryRequest();
                request.setPostId(form.getPostId());

                return request;
            }

            @Override
            public PostQueryResult doProcess(PostQueryRequest request) {
                return postFacade.queryDetail(request);
            }
        });
    }

    @PostMapping("/query")
    public ResponseEntity<PostQueryListResult> queryList(@RequestHeader HttpHeaders headers, @RequestBody PostQueryListForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<PostQueryListResult, PostQueryListRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public PostQueryListRequest composeRequest() {
                PostQueryListRequest request = new PostQueryListRequest();
                request.setTitle(form.getTitle());
                request.setTags(form.getTags());
                request.setPageNumber(form.getPageNumber());
                request.setNumberOfItem(form.getNumberOfItem());

                return request;
            }

            @Override
            public PostQueryListResult doProcess(PostQueryListRequest request) {
                return postFacade.queryList(request);
            }
        });
    }
}
