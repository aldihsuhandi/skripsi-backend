package id.thesis.shumishumi.common.process.processor.post;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.summary.PostSummary;
import id.thesis.shumishumi.common.model.viewobject.PostVO;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.process.processor.BaseProcessor;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.constant.CommonConst;
import id.thesis.shumishumi.common.util.converter.SummaryConverter;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.post.PostQueryRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.result.post.PostQueryResult;
import id.thesis.shumishumi.core.service.PostService;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

public class PostQueryProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostService postService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        PostQueryRequest request = (PostQueryRequest) baseRequest;
        PostQueryResult result = (PostQueryResult) baseResult;


        PostVO postVO = postService.queryById(request.getPostId());
        AssertUtil.isNotNull(postVO, "post not found", ShumishumiErrorCodeEnum.POST_NOT_FOUND);

        String userId = "";
        SessionVO sessionVO = sessionService.query(request.getSessionId());
        if (sessionVO != null) {
            userId = sessionVO.getUserId();
        }

        UserVO poster = userService.queryById(postVO.getUserId(), true);
        int currentUserVote = postService.queryPostVote(userId, postVO.getPostId());
        if (currentUserVote == CommonConst.INT_NOT_FOUND) {
            currentUserVote = 0;
        }

        PostSummary post = SummaryConverter.toSummary(postVO, currentUserVote);
        post.setUserInfo(SummaryConverter.toSummary(poster));

        result.setPost(post);
    }
}
