package id.thesis.shumishumi.foundation.dalgen.service;

import id.thesis.shumishumi.foundation.dalgen.model.request.ImageDAORequest;
import id.thesis.shumishumi.foundation.dalgen.model.result.ImageDO;

public interface ImageDAO {
    void insert(ImageDAORequest request);

    ImageDO query(ImageDAORequest request);
}
