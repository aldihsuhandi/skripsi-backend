package id.thesis.shumishumi.service.api;

import id.thesis.shumishumi.core.callback.ProcessCallback;
import id.thesis.shumishumi.core.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.facade.api.ImageFacade;
import id.thesis.shumishumi.facade.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.facade.request.image.ImageDownloadRequest;
import id.thesis.shumishumi.facade.request.image.ImageUploadRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.image.ImageDownloadResult;
import id.thesis.shumishumi.facade.result.image.ImageUploadResult;
import org.springframework.stereotype.Service;

@Service
public class ImageFacadeImpl extends ProcessFacade implements ImageFacade {
    @Override
    public ImageDownloadResult download(ImageDownloadRequest request) {
        return (ImageDownloadResult) ProcessCallbackSupport.process(ProcessTypeEnum.IMAGE_DOWNLOAD, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new ImageDownloadResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }

    @Override
    public ImageUploadResult upload(ImageUploadRequest request) {
        return (ImageUploadResult) ProcessCallbackSupport.process(ProcessTypeEnum.IMAGE_UPLOAD, request, new ProcessCallback() {
            @Override
            public BaseResult initResult() {
                return new ImageUploadResult();
            }

            @Override
            public void process(ProcessTypeEnum processType, BaseResult result) {
                doProcess(request, result, processType);
            }
        });
    }
}
