package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.context.PagingContext;
import id.thesis.shumishumi.common.model.viewobject.PostVO;

import java.util.List;

public interface PostService {

    String create(PostVO post);

    void update(PostVO post);

    void delete(String postId);

    PostVO queryById(String postId);

    List<PostVO> queryList(String title, List<String> tags, PagingContext pagingContext);

    int countList(String title, List<String> tags);

    void insertVote(String userId, String postId, int value);

    void updateVote(String userId, String postId, int value);

    int queryPostVote(String userId, String postId);
}
