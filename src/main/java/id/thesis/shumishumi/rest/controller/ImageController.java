package id.thesis.shumishumi.rest.controller;

import id.thesis.shumishumi.common.process.callback.ControllerCallback;
import id.thesis.shumishumi.common.process.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.core.facade.ImageFacade;
import id.thesis.shumishumi.core.request.image.ImageDownloadRequest;
import id.thesis.shumishumi.core.result.image.ImageDownloadResult;
import id.thesis.shumishumi.rest.form.image.ImageDownloadForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/image")
public class ImageController extends BaseController {

    @Autowired
    private ImageFacade imageFacade;

    @PostMapping(value = "/download", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    public ResponseEntity<ImageDownloadResult> download(@RequestHeader HttpHeaders headers, @RequestBody ImageDownloadForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.IMAGE_PNG, new ControllerCallback<ImageDownloadResult, ImageDownloadRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public ImageDownloadRequest composeRequest() {
                ImageDownloadRequest request = new ImageDownloadRequest();
                request.setImageId(form.getImageId());

                return request;
            }

            @Override
            public ImageDownloadResult doProcess(ImageDownloadRequest request) {
                return imageFacade.download(request);
            }
        });
    }
}
