package id.thesis.shumishumi.core.processor.post;

import id.thesis.shumishumi.common.service.PostService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.PostVO;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.post.PostCreateRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.post.PostCreateResult;
import org.springframework.beans.factory.annotation.Autowired;

public class PostCreateProcessor implements BaseProcessor {

    @Autowired
    private PostService postService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        PostCreateRequest request = (PostCreateRequest) baseRequest;
        PostCreateResult result = (PostCreateResult) baseResult;

        SessionVO sessionVO = sessionService.query(request.getSessionId());

        UserVO user = userService.queryById(sessionVO.getUserId(), true);
        AssertUtil.isNotNull(user, "user not exist", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(user.isActive(), "user not active", ShumishumiErrorCodeEnum.USER_NOT_ACTIVE);
        AssertUtil.isExpected(!user.isDeleted(), "user not exist", ShumishumiErrorCodeEnum.USER_NOT_FOUND);

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
