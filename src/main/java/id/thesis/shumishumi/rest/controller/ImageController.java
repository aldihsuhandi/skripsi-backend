package id.thesis.shumishumi.rest.controller;

import id.thesis.shumishumi.common.model.context.ResultContext;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.process.callback.ControllerCallback;
import id.thesis.shumishumi.common.process.callback.ControllerCallbackSupport;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.core.facade.ImageFacade;
import id.thesis.shumishumi.core.request.image.ImageDownloadRequest;
import id.thesis.shumishumi.core.request.image.ImageUploadRequest;
import id.thesis.shumishumi.core.result.image.ImageDownloadResult;
import id.thesis.shumishumi.core.result.image.ImageUploadResult;
import id.thesis.shumishumi.rest.form.image.ImageDownloadForm;
import id.thesis.shumishumi.rest.form.image.ImageUploadForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/image")
public class ImageController extends BaseController {

    @Autowired
    private ImageFacade imageFacade;

    @PostMapping("/download")
    public ResponseEntity<byte[]> download(@RequestHeader HttpHeaders headers, @RequestBody ImageDownloadForm form) throws IOException {
        ResponseEntity<ImageDownloadResult> result = ControllerCallbackSupport.process(headers, form, MediaType.IMAGE_JPEG, new ControllerCallback<ImageDownloadResult, ImageDownloadRequest>() {
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


        ResultContext resultContext = result.getBody().getResultContext();
        byte[] image = new byte[0];
        try {
            image = result.getBody().getImage().getBytes();
        } catch (Exception e) {
            LogUtil.exception(e.getMessage(), e);
            resultContext.setResultMsg(e.getMessage());
            resultContext.setSuccess(false);
            resultContext.setResultCode(ShumishumiErrorCodeEnum.SYSTEM_ERROR.getErrorCode());
        }

        HttpHeaders respHeaders = new HttpHeaders();
        respHeaders.set("traceId", result.getHeaders().getFirst("traceId"));
        respHeaders.set("success", String.valueOf(resultContext.isSuccess()));

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .headers(respHeaders)
                .body(image);
    }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ImageUploadResult> upload(@RequestHeader HttpHeaders headers, @ModelAttribute ImageUploadForm form) {
        return ControllerCallbackSupport.process(headers, form, MediaType.APPLICATION_JSON, new ControllerCallback<ImageUploadResult, ImageUploadRequest>() {
            @Override
            public void authCheck(String clientId, String clientSecret) {
                authenticate(clientId, clientSecret);
            }

            @Override
            public ImageUploadRequest composeRequest() {
                ImageUploadRequest request = new ImageUploadRequest();
                request.setImage(form.getImage());

                return request;
            }

            @Override
            public ImageUploadResult doProcess(ImageUploadRequest request) {
                return imageFacade.upload(request);
            }
        });
    }
}
