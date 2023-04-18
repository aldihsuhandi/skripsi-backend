package id.thesis.shumishumi.core.processor.post;

import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.core.service.PostService;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.PostVO;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.post.PostDeleteRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

public class PostDeleteProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private PostService postService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        PostDeleteRequest request = (PostDeleteRequest) baseRequest;

        SessionVO session = sessionService.query(request.getSessionId());
        PostVO post = postService.queryById(request.getPostId());

        AssertUtil.isExpected(session.getUserId(), post.getUserId(),
                ShumishumiErrorCodeEnum.USER_INVALID.getErrorMsg(), ShumishumiErrorCodeEnum.USER_INVALID);
        AssertUtil.isNotNull(post, "post not found", ShumishumiErrorCodeEnum.POST_NOT_FOUND);

        postService.delete(post.getPostId());
    }
}
