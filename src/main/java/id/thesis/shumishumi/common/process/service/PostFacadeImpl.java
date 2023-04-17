package id.thesis.shumishumi.common.process.service;

import id.thesis.shumishumi.common.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.common.process.callback.ProcessCallback;
import id.thesis.shumishumi.common.process.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.core.facade.PostFacade;
import id.thesis.shumishumi.core.request.post.PostCreateRequest;
import id.thesis.shumishumi.core.request.post.PostDeleteRequest;
import id.thesis.shumishumi.core.request.post.PostDownvoteRequest;
import id.thesis.shumishumi.core.request.post.PostEditRequest;
import id.thesis.shumishumi.core.request.post.PostQueryListRequest;
import id.thesis.shumishumi.core.request.post.PostQueryRequest;
import id.thesis.shumishumi.core.request.post.PostUpvoteRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.result.post.PostCreateResult;
import id.thesis.shumishumi.core.result.post.PostDeleteResult;
import id.thesis.shumishumi.core.result.post.PostDownvoteResult;
import id.thesis.shumishumi.core.result.post.PostEditResult;
import id.thesis.shumishumi.core.result.post.PostQueryListResult;
import id.thesis.shumishumi.core.result.post.PostQueryResult;
import id.thesis.shumishumi.core.result.post.PostUpvoteResult;
import org.springframework.stereotype.Service;

@Service
public class PostFacadeImpl extends ProcessFacade implements PostFacade {
    @Override
    public PostCreateResult create(PostCreateRequest request) {
        return (PostCreateResult) ProcessCallbackSupport.process(ProcessTypeEnum.POST_CREATE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new PostCreateResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public PostEditResult edit(PostEditRequest request) {
        return (PostEditResult) ProcessCallbackSupport.process(ProcessTypeEnum.POST_EDIT, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new PostEditResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public PostDeleteResult delete(PostDeleteRequest request) {
        return (PostDeleteResult) ProcessCallbackSupport.process(ProcessTypeEnum.POST_DELETE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new PostDeleteResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public PostUpvoteResult upvote(PostUpvoteRequest request) {
        return (PostUpvoteResult) ProcessCallbackSupport.process(ProcessTypeEnum.POST_UPVOTE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new PostUpvoteResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public PostDownvoteResult downvote(PostDownvoteRequest request) {
        return (PostDownvoteResult) ProcessCallbackSupport.process(ProcessTypeEnum.POST_DOWNVOTE, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new PostDownvoteResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public PostQueryResult queryDetail(PostQueryRequest request) {
        return (PostQueryResult) ProcessCallbackSupport.process(ProcessTypeEnum.POST_DETAIL_QUERY, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new PostQueryResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public PostQueryListResult queryList(PostQueryListRequest request) {
        return (PostQueryListResult) ProcessCallbackSupport.process(ProcessTypeEnum.POST_QUERY, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new PostQueryListResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }
}
