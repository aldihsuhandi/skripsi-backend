package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.result.CommentVoteDO;

public interface CommentVoteDAO {
    int queryVote(String commentId, int value);

    CommentVoteDO queryUserVote(String commentId, String userId);

    void insert(String userId, String commentId, int value);

    void update(String userId, String commentId, int value);
}
