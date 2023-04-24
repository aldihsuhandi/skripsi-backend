package id.thesis.shumishumi.foundation.seeders;

import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.ImageDO;
import id.thesis.shumishumi.foundation.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;

public abstract class BaseSeeder {

    protected static Logger LOGGER = LoggerFactory.getLogger(LogConstant.SEEDERS_LOGGER);

    @Autowired
    private ImageRepository imageRepository;

    protected int order;

    protected String seederName;

    abstract void setOrder();

    abstract void setSeederName();

    abstract void deleteRecords();

    public void delete() {
        LogUtil.info(LOGGER, String.format("delete all record on %s", seederName));
        deleteRecords();
    }

    abstract void seed();

    public int getOrder() {
        return order;
    }

    public BaseSeeder() {
        setOrder();
        setSeederName();
    }

    public String getSeederName() {
        return seederName;
    }

    public void startSeeding() {
        LogUtil.info(LOGGER, String.format("%s start seeding", seederName));
        seed();

        LogUtil.info(LOGGER, String.format("%s finish seeding", seederName));
    }

    protected String downloadImage(String url) {
        LogUtil.info(LOGGER, String.format("downloadImageRequest[url=%s]", url));
        RestTemplate template = new RestTemplate();
        byte[] imageBytes = template.getForObject(url, byte[].class);
        if (imageBytes == null) {
            return "";
        }

        Blob imageBlob = null;
        try {
            imageBlob = new SerialBlob(imageBytes);
        } catch (Exception e) {
            throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        ImageDO imageDO = new ImageDO();
        imageDO.setImageId(FunctionUtil.generateUUID());
        imageDO.setImageName(url);
        imageDO.setImageExt("image/jpeg");
        imageDO.setImage(imageBlob);

        imageRepository.save(imageDO);

        return imageDO.getImageId();
    }
}
