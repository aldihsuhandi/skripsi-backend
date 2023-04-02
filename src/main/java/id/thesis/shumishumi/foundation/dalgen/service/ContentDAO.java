package id.thesis.shumishumi.foundation.dalgen.service;

import id.thesis.shumishumi.foundation.dalgen.model.result.ContentDO;

public interface ContentDAO {
    ContentDO queryContent(String contentName);
}
