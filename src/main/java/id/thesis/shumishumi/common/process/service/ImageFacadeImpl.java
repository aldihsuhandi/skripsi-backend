package id.thesis.shumishumi.common.process.service;

import id.thesis.shumishumi.common.model.enumeration.ProcessTypeEnum;
import id.thesis.shumishumi.common.process.callback.ProcessCallback;
import id.thesis.shumishumi.common.process.callback.ProcessCallbackSupport;
import id.thesis.shumishumi.core.facade.ImageFacade;
import id.thesis.shumishumi.core.request.image.ImageDownloadRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.result.image.ImageDownloadResult;
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
}
