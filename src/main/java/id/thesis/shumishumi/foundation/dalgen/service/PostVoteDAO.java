package id.thesis.shumishumi.foundation.dalgen.service;

import id.thesis.shumishumi.foundation.dalgen.model.result.PostVoteDO;

public interface PostVoteDAO {
    int queryVote(String postId, int value);

    PostVoteDO queryUserVote(String postId, String userId);

    void insert(String userId, String postId, int value);

    void update(String userId, String postId, int value);
}
