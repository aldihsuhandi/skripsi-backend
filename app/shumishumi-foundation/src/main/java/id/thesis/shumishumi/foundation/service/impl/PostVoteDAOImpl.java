package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.PostVoteDO;
import id.thesis.shumishumi.foundation.model.result.primarykey.PostVoteDOPK;
import id.thesis.shumishumi.foundation.repository.PostVoteRepository;
import id.thesis.shumishumi.foundation.service.PostVoteDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostVoteDAOImpl implements PostVoteDAO {
    private static final Logger DALGEN = LoggerFactory.getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private PostVoteRepository postVoteRepository;


    @Override
    public int queryVote(String postId, int value) {
        LogUtil.info(DALGEN, String.format("postVoteDAO#queryVote[postId=%s,value=%d]", postId, value));

        int count = 0;
        try {
            count = postVoteRepository.countPostVote(postId, value);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN, String.format("postVoteDAO#queryVote[result=%d]", count));

        return count;
    }

    @Override
    public PostVoteDO queryUserVote(String postId, String userId) {
        LogUtil.info(DALGEN, String.format("postVoteDAO#queryUserVote[postId=%s,userId=%s]",
                postId, userId));

        PostVoteDO result;
        try {
            result = postVoteRepository.findById(new PostVoteDOPK(postId, userId)).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN, String.format("postVoteDAO#queryUserVote[result=%s]", result));
        return result;
    }

    @Override
    public void insert(String userId, String postId, int value) {
        LogUtil.info(DALGEN, String.format("postVoteDAO#insert[userId=%s,postId=%s,value=%s]"
                , userId, postId, value));

        PostVoteDO vote = new PostVoteDO();
        vote.setPk(new PostVoteDOPK(postId, userId));
        vote.setValue(value);

        try {
            postVoteRepository.save(vote);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void update(String userId, String postId, int value) {
        LogUtil.info(DALGEN, String.format("postVoteDAO#update[userId=%s,postId=%s,value=%s]"
                , userId, postId, value));

        PostVoteDO vote = new PostVoteDO();
        vote.setPk(new PostVoteDOPK(postId, userId));
        vote.setValue(value);

        try {
            postVoteRepository.save(vote);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }
}
