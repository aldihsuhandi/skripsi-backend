package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.request.PostDAORequest;
import id.thesis.shumishumi.foundation.model.result.PostDO;

import java.util.List;

public interface PostDAO {

    void insert(PostDAORequest request);

    void update(PostDAORequest request);

    void delete(String postId);

    PostDO queryById(String postId);

    List<PostDO> query(PostDAORequest request);

    long countList(PostDAORequest request);
}
