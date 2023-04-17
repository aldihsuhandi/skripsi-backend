package id.thesis.shumishumi.common.process.processor.post;

import id.thesis.shumishumi.common.model.viewobject.PostVO;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.common.process.processor.BaseProcessor;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.post.PostCreateRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.result.post.PostCreateResult;
import id.thesis.shumishumi.core.service.PostService;
import id.thesis.shumishumi.core.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostCreateProcessor implements BaseProcessor {

    @Autowired
    private PostService postService;

    @Autowired
    private SessionService sessionService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        PostCreateRequest request = (PostCreateRequest) baseRequest;
        PostCreateResult result = (PostCreateResult) baseResult;

        SessionVO sessionVO = sessionService.query(request.getSessionId());

        PostVO post = new PostVO();
        post.setUserId(sessionVO.getUserId());
        post.setTitle(request.getTitle());
        post.setContent(request.getContent());
        post.setTags(request.getTags());
        post.setImages(request.getImages());

        String postId = postService.create(post);

        result.setPostId(postId);
    }
}
