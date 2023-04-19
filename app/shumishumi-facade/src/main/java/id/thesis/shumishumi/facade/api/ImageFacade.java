package id.thesis.shumishumi.facade.api;

import id.thesis.shumishumi.facade.request.image.ImageDownloadRequest;
import id.thesis.shumishumi.facade.request.image.ImageUploadRequest;
import id.thesis.shumishumi.facade.result.image.ImageDownloadResult;
import id.thesis.shumishumi.facade.result.image.ImageUploadResult;

public interface ImageFacade {
    ImageDownloadResult download(ImageDownloadRequest request);

    ImageUploadResult upload(ImageUploadRequest request);
}
