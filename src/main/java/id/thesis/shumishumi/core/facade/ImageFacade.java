package id.thesis.shumishumi.core.facade;

import id.thesis.shumishumi.core.request.image.ImageDownloadRequest;
import id.thesis.shumishumi.core.result.image.ImageDownloadResult;

public interface ImageFacade {
    ImageDownloadResult download(ImageDownloadRequest request);
}
