package id.thesis.shumishumi.foundation.dalgen.service;

import id.thesis.shumishumi.foundation.dalgen.model.request.PostDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.result.PostDO;

import java.util.List;

public interface PostDAO {

    void insert(PostDAORequest request);

    void update(PostDAORequest request);

    void delete(String postId);

    PostDO queryById(String postId);

    List<PostDO> query(PostDAORequest request);

    int countList(PostDAORequest request);
}
