package id.thesis.shumishumi.foundation.seeders;

import id.thesis.shumishumi.foundation.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostSeeder extends BaseSeeder {

    @Autowired
    private PostRepository postRepository;

    @Override
    void setOrder() {
        this.order = 10;
    }

    @Override
    void setSeederName() {
        this.seederName = "post seeder";
    }

    @Override
    void deleteRecords() {
        postRepository.deleteAll();
    }

    @Override
    void seed() {

    }
}
