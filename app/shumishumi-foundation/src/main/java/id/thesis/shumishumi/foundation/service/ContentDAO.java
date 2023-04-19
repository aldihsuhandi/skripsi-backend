package id.thesis.shumishumi.foundation.service;


import id.thesis.shumishumi.foundation.model.result.ContentDO;

public interface ContentDAO {
    ContentDO queryContent(String contentName);
}
