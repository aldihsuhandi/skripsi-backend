package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.service.ContentService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.ContentDO;
import id.thesis.shumishumi.foundation.service.ContentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private ContentDAO contentDAO;

    @Override
    public String queryContent(String contentName) {
        ContentDO contentDO = contentDAO.queryContent(contentName);
        AssertUtil.isNotNull(contentDO, "content doesn't exist", ShumishumiErrorCodeEnum.SYSTEM_ERROR);

        return contentDO.getContent();
    }
}
