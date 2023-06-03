package id.thesis.shumishumi.foundation.service.impl;

import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.constant.LogConstant;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.request.ImageDAORequest;
import id.thesis.shumishumi.foundation.model.result.ImageDO;
import id.thesis.shumishumi.foundation.repository.ImageRepository;
import id.thesis.shumishumi.foundation.service.ImageDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageDAOImpl implements ImageDAO {

    private static final Logger DALGEN_LOGGER = LoggerFactory.
            getLogger(LogConstant.DALGEN_LOGGER);

    @Autowired
    private ImageRepository imageRepository;

    @Override
    public void insert(ImageDO request) {
        LogUtil.info(DALGEN_LOGGER, String.format("imageDAO#insert[request=%s]", request));

        try {
            imageRepository.save(request);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }
    }

    @Override
    public ImageDO query(ImageDAORequest request) {
        LogUtil.info(DALGEN_LOGGER, String.format("imageDAO#query[request=%s]", request));

        ImageDO image = null;
        try {
            image = imageRepository.findById(request.getImageId()).orElse(null);
        } catch (Exception e) {
            throw new ShumishumiException(e, ShumishumiErrorCodeEnum.SYSTEM_ERROR);
        }

        LogUtil.info(DALGEN_LOGGER, String.format("imageDAO#query[result=%s]", image));
        return image;
    }
}
