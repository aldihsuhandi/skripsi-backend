package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.context.PagingContext;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.request.PostDAORequest;
import id.thesis.shumishumi.foundation.model.result.PostDO;
import id.thesis.shumishumi.foundation.repository.PostRepository;
import id.thesis.shumishumi.foundation.service.PostDAO;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostDAOImpl implements PostDAO {

    private static final Logger DALGEN = LoggerFactory.getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private PostRepository postRepository;

    @Override
    public void insert(PostDAORequest request) {
        LogUtil.info(DALGEN, String.format("postDAO#insert[request=%s]", request));

        PostDO post = convertFromDAORequest(request);
        try {
            postRepository.save(post);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void update(PostDAORequest request) {
        LogUtil.info(DALGEN, String.format("postDAO#update[request=%s]", request));

        PostDO post = convertFromDAORequest(request);
        try {
            postRepository.save(post);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public void delete(String postId) {
        LogUtil.info(DALGEN, String.format("postDAO#delete[postId=%s]", postId));
        try {
            postRepository.softDelete(postId);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }


    @Override
    public PostDO queryById(String postId) {
        LogUtil.info(DALGEN, String.format("postDAO#queryById[postId=%s]", postId));

        PostDO result;
        try {
            result = postRepository.findById(postId).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN, String.format("postDAO#queryById[result=%s]", result));
        return result;
    }

    @Override
    public List<PostDO> query(PostDAORequest request) {
        LogUtil.info(DALGEN, String.format("postDAO#query[request=%s]", request));

        PagingContext pagingContext = new PagingContext();

        Pageable pageable = PageRequest.of(pagingContext.getPageNumber() - 1, pagingContext.getNumberOfItem());

        List<PostDO> posts = new ArrayList<>();
        try {
            List<String> tags = new ArrayList<>();
            if (StringUtils.isNotEmpty(request.getTags())) {
                tags = List.of(request.getTags().split(CommonConst.SEPARATOR_SPLIT));
            }
            posts = postRepository.queryFilter(request.getTitle(),
                    request.getUserId(), tags, pageable).getContent();
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN, String.format("postDAO#query[result=%s]", posts));
        return posts;
    }

    @Override
    public long countList(PostDAORequest request) {
        LogUtil.info(DALGEN, String.format("postDAO#countList[request=%s]", request));

        long count = 0;
        try {
            List<String> tags = new ArrayList<>();
            if (StringUtils.isNotEmpty(request.getTags())) {
                tags = List.of(request.getTags().split(CommonConst.SEPARATOR_SPLIT));
            }
            count = postRepository.countWithFilter(request.getTitle(),
                    request.getUserId(), tags);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN, String.format("postDAO#countList[result=%s]", count));
        return count;
    }

    private PostDO convertFromDAORequest(PostDAORequest daoRequest) {
        if (daoRequest == null) {
            return null;
        }

        PostDO postDO = new PostDO();
        postDO.setPostId(daoRequest.getPostId());
        postDO.setUserId(daoRequest.getUserId());
        postDO.setTitle(daoRequest.getTitle());
        postDO.setContent(daoRequest.getContent());
        postDO.setTags(daoRequest.getTags());
        postDO.setImages(daoRequest.getImages());
        postDO.setDeleted(false);

        return postDO;
    }
}
