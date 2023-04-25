package id.thesis.shumishumi.core.processor.post;

import id.thesis.shumishumi.common.service.PostService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.core.converter.SummaryConverter;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.summary.PostSummary;
import id.thesis.shumishumi.facade.model.viewobject.PostVO;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.post.PostQueryListRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.post.PostQueryListResult;
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

        long totalData = postService.countList(request.getTitle(), request.getTags());
        PagingContext pagingContext = new PagingContext(request.getPageNumber(), request.getNumberOfItem(), totalData);

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
