package id.thesis.shumishumi.foundation.service;

import id.thesis.shumishumi.foundation.model.request.ImageDAORequest;
import id.thesis.shumishumi.foundation.model.result.ImageDO;

public interface ImageDAO {
    void insert(ImageDAORequest request);

    ImageDO query(ImageDAORequest request);
}
