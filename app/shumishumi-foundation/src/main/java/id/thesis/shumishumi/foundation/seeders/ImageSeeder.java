package id.thesis.shumishumi.foundation.seeders;

import id.thesis.shumishumi.foundation.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageSeeder extends BaseSeeder {

    @Autowired
    private ImageRepository imageRepository;

    @Override
    void setOrder() {
        this.order = 0;
    }

    @Override
    void setSeederName() {
        this.seederName = "image seeder";
    }

    @Override
    void deleteRecords() {
        imageRepository.deleteAll();
    }

    @Override
    void seed() {
        ;
    }
}
