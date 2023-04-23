package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.PostDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostRepositoryCustom {
    Page<PostDO> queryFilter(String title, String userId, List<String> tags, Pageable pageable);

    long countWithFilter(String title, String userId, List<String> tags);
}
