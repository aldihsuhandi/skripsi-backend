package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.viewobject.PostVO;

import java.util.List;

public interface PostService {
    PostVO queryById(String postId);

    List<PostVO> queryList(String title, List<String> tags);

    void postVote(String userId, String postId, int value);

    int queryPostVote(String userId, String postId);
}
