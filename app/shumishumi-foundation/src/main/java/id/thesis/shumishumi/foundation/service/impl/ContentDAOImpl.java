package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.ContentDO;
import id.thesis.shumishumi.foundation.repository.ContentRepository;
import id.thesis.shumishumi.foundation.service.ContentDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentDAOImpl implements ContentDAO {
    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private ContentRepository contentRepository;

    @Override
    public ContentDO queryContent(String contentName) {
        LogUtil.info(DALGEN_LOGGER, String.format("contentDAO#queryContent[contentName=%s]", contentName));

        ContentDO content = null;
        try {
            content = contentRepository.findById(contentName).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("contentDAO#queryContent[result=%s]", content));
        return content;
    }
}
