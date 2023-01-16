package id.thesis.shumishumi.dalgen.service;

import id.thesis.shumishumi.dalgen.model.result.ContentDO;

public interface ContentDAO {
    ContentDO queryContent(String contentName);
}
