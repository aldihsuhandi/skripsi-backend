package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.facade.api.ImageFacade;
import id.thesis.shumishumi.facade.request.image.ImageDownloadRequest;
import id.thesis.shumishumi.facade.request.image.ImageUploadRequest;
import id.thesis.shumishumi.facade.result.image.ImageDownloadResult;
import id.thesis.shumishumi.facade.result.image.ImageUploadResult;
import id.thesis.shumishumi.foundation.model.result.ImageDO;
import id.thesis.shumishumi.test.util.ResultAssert;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;

import javax.sql.rowset.serial.SerialBlob;

public class ImageFacadeTest extends FacadeTestBase {
    @Autowired
    private ImageFacade imageFacade;

    @Test
    public void imageUploadTest_SUCCESS() {
        ImageUploadRequest request = new ImageUploadRequest();
        request.setImage(new MockMultipartFile("itemImage", new byte[0]));

        ImageUploadResult result = imageFacade.upload(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    @SneakyThrows
    public void imageDownloadTest_SUCCESS() {
        ImageDownloadRequest request = new ImageDownloadRequest();
        request.setImageId("imageID");

        ImageDO imageDO = new ImageDO();
        imageDO.setImageExt("image/jpeg");
        imageDO.setImageName("imageName");
        imageDO.setImage(new SerialBlob(new byte[]{-1, -2, -3, -4, -5}));

        Mockito.when(imageDAO.query(Mockito.any())).thenReturn(imageDO);

        ImageDownloadResult result = imageFacade.download(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }
}
