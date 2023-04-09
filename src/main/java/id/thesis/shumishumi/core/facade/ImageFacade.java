package id.thesis.shumishumi.core.facade;

import id.thesis.shumishumi.core.request.image.ImageDownloadRequest;
import id.thesis.shumishumi.core.request.image.ImageUploadRequest;
import id.thesis.shumishumi.core.result.image.ImageDownloadResult;
import id.thesis.shumishumi.core.result.image.ImageUploadResult;

public interface ImageFacade {
    ImageDownloadResult download(ImageDownloadRequest request);

    ImageUploadResult upload(ImageUploadRequest request);
}
