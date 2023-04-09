package id.thesis.shumishumi.core.service;

import id.thesis.shumishumi.common.model.viewobject.ImageVO;

public interface ImageService {
    ImageVO download(String imageId);

    void upload(ImageVO imageVO);
}
