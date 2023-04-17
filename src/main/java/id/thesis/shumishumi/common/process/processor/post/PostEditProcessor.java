package id.thesis.shumishumi.common.process.processor.post;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.viewobject.PostVO;
import id.thesis.shumishumi.common.process.processor.BaseProcessor;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.post.PostEditRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.service.PostService;
import id.thesis.shumishumi.core.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostEditProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private PostService postService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        PostEditRequest request = (PostEditRequest) baseRequest;

        String userId = sessionService.query(request.getSessionId()).getUserId();
        PostVO post = postService.queryById(request.getPostId());
        AssertUtil.isExpected(userId, post.getUserId(),
                ShumishumiErrorCodeEnum.USER_INVALID.getErrorMsg(), ShumishumiErrorCodeEnum.USER_INVALID);
        AssertUtil.isNotNull(post, "post not found", ShumishumiErrorCodeEnum.POST_NOT_FOUND);

        FunctionUtil.updatePostField(post, request);
        postService.update(post);
    }
}
