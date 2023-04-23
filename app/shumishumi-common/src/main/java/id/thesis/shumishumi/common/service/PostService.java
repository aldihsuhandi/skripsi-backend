package id.thesis.shumishumi.common.service;

import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.viewobject.PostVO;

import java.util.List;

public interface PostService {

    String create(PostVO post);

    void update(PostVO post);

    void delete(String postId);

    PostVO queryById(String postId);

    List<PostVO> queryList(String title, List<String> tags, PagingContext pagingContext);

    long countList(String title, List<String> tags);

    void insertVote(String userId, String postId, int value);

    void updateVote(String userId, String postId, int value);

    int queryPostVote(String userId, String postId);
}
