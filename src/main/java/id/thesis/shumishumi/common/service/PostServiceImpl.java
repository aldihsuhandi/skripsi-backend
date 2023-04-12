package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.common.model.viewobject.PostVO;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.common.util.constant.CommonConst;
import id.thesis.shumishumi.common.util.converter.ViewObjectConverter;
import id.thesis.shumishumi.core.service.PostService;
import id.thesis.shumishumi.foundation.dalgen.model.request.PostDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.result.PostDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.PostVoteDO;
import id.thesis.shumishumi.foundation.dalgen.service.PostDAO;
import id.thesis.shumishumi.foundation.dalgen.service.PostVoteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostDAO postDAO;

    @Autowired
    private PostVoteDAO postVoteDAO;

    @Override
    public void create(PostVO post) {

        StringBuilder imageSb = new StringBuilder();
        StringBuilder tagSb = new StringBuilder();

        post.getImages().forEach(image ->
                imageSb.append(image).append(CommonConst.SEPARATOR));
        post.getTags().forEach(tag ->
                tagSb.append(tag).append(CommonConst.SEPARATOR));

        PostDAORequest daoRequest = new PostDAORequest();
        daoRequest.setPostId(FunctionUtil.generateUUID());
        daoRequest.setTitle(post.getTitle());
        daoRequest.setContent(post.getContent());
        daoRequest.setUserId(post.getUserId());
        daoRequest.setImages(imageSb.substring(0, imageSb.length() - 1));
        daoRequest.setTitle(tagSb.substring(0, tagSb.length() - 1));

        postDAO.insert(daoRequest);
    }

    @Override
    public void update(PostVO post) {
        StringBuilder imageSb = new StringBuilder();
        StringBuilder tagSb = new StringBuilder();

        post.getImages().forEach(image ->
                imageSb.append(image).append(CommonConst.SEPARATOR));
        post.getTags().forEach(tag ->
                tagSb.append(tag).append(CommonConst.SEPARATOR));

        PostDAORequest daoRequest = new PostDAORequest();
        daoRequest.setPostId(post.getPostId());
        daoRequest.setTitle(post.getTitle());
        daoRequest.setContent(post.getContent());
        daoRequest.setUserId(post.getUserId());
        daoRequest.setImages(imageSb.substring(0, imageSb.length() - 1));
        daoRequest.setTitle(tagSb.substring(0, tagSb.length() - 1));

        postDAO.update(daoRequest);
    }

    @Override
    public PostVO queryById(String postId) {
        PostDO postDO = postDAO.queryById(postId);
        PostVO vo = ViewObjectConverter.toViewObject(postDO);
        composePostVote(vo);

        return vo;
    }

    @Override
    public List<PostVO> queryList(String title, List<String> tags) {
        StringBuilder tagsSb = new StringBuilder();
        tags.forEach(tag -> {
            tagsSb.append(tag).append(CommonConst.SEPARATOR);
        });

        PostDAORequest daoRequest = new PostDAORequest();
        daoRequest.setTitle(title);
        daoRequest.setTags(tagsSb.substring(0, tagsSb.length() - 1));

        return postDAO.query(daoRequest).stream().map(postDO -> {
            PostVO vo = ViewObjectConverter.toViewObject(postDO);
            composePostVote(vo);
            return vo;
        }).collect(Collectors.toList());
    }

    @Override
    public void insertVote(String userId, String postId, int value) {
        postVoteDAO.insert(userId, postId, value);
    }

    @Override
    public void updateVote(String userId, String postId, int value) {
        postVoteDAO.update(userId, postId, value);
    }

    @Override
    public int queryPostVote(String userId, String postId) {
        PostVoteDO vote = postVoteDAO.queryUserVote(postId, userId);
        if (vote == null) {
            return CommonConst.INT_NOT_FOUND;
        }
        return vote.getValue();
    }

    private void composePostVote(PostVO post) {
        int upvote = postVoteDAO.queryVote(post.getPostId(), 1);
        int downvote = postVoteDAO.queryVote(post.getPostId(), -1);

        post.setUpvote(upvote);
        post.setDownvote(downvote);
    }
}
