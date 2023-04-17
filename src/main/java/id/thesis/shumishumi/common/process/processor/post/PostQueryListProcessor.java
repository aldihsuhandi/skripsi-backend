package id.thesis.shumishumi.common.process.processor.post;

import id.thesis.shumishumi.common.model.context.PagingContext;
import id.thesis.shumishumi.common.model.summary.PostSummary;
import id.thesis.shumishumi.common.model.viewobject.PostVO;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.process.processor.BaseProcessor;
import id.thesis.shumishumi.common.util.constant.CommonConst;
import id.thesis.shumishumi.common.util.converter.SummaryConverter;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.post.PostQueryListRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.result.post.PostQueryListResult;
import id.thesis.shumishumi.core.service.PostService;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class PostQueryListProcessor implements BaseProcessor {

    @Autowired
    private PostService postService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        PostQueryListRequest request = (PostQueryListRequest) baseRequest;
        PostQueryListResult result = (PostQueryListResult) baseResult;

        int totalData = postService.countList(request.getTitle(), request.getTags());
        PagingContext pagingContext = new PagingContext(request.getPageNumber(), request.getNumberOfItem(), (long) totalData);

        List<PostVO> posts = postService.queryList(
                request.getTitle(), request.getTags(), pagingContext);

        String userId;
        SessionVO sessionVO = sessionService.query(request.getSessionId());
        if (sessionVO != null) {
            userId = sessionVO.getUserId();
        } else {
            userId = "";
        }

        List<PostSummary> postSummaries = posts.stream().map(post -> {
            int currentUserVote = postService.queryPostVote(userId, post.getPostId());
            if (currentUserVote == CommonConst.INT_NOT_FOUND) {
                currentUserVote = 0;
            }

            UserVO userVO = userService.queryById(post.getUserId(), true);

            PostSummary postSummary = SummaryConverter.toSummary(post, currentUserVote);
            postSummary.setUserInfo(SummaryConverter.toSummary(userVO));

            return postSummary;
        }).collect(Collectors.toList());

        result.setPosts(postSummaries);
        result.setPagingContext(pagingContext);
    }
}
