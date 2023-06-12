package id.thesis.shumishumi.foundation.seeders;

import id.thesis.shumishumi.facade.exception.ShumishumiException;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.foundation.model.result.HobbyDO;
import id.thesis.shumishumi.foundation.repository.HobbyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HobbySeeder extends BaseSeeder {

    @Autowired
    private HobbyRepository hobbyRepository;

    @Override
    public void setOrder() {
        this.order = 1;
    }

    @Override
    void setSeederName() {
        this.seederName = "hobby seeder";
    }

    @Override
    void deleteRecords() {
        hobbyRepository.deleteAll();
    }

    @Override
    void seed() {
        List<HobbyRequest> requests = new ArrayList<>();
        requests.add(new HobbyRequest("KEYBOARD", "Keyboard"));
        requests.add(new HobbyRequest("COMPUTER", "Computer"));
        requests.add(new HobbyRequest("MUSIC", "Music"));
        requests.add(new HobbyRequest("COFFEE", "Coffee"));

        requests.forEach(request -> {
            HobbyDO hobbyDO = new HobbyDO();
            hobbyDO.setHobbyName(request.hobbyName);
            hobbyDO.setHobbyId(request.hobbyId);

            try {
                hobbyRepository.save(hobbyDO);
            } catch (Exception e) {
                throw new ShumishumiException(e.getMessage(), ShumishumiErrorCodeEnum.SYSTEM_ERROR);
            }
        });
    }

    private static class HobbyRequest {
        String hobbyId;
        String hobbyName;

        public HobbyRequest(String hobbyId, String hobbyName) {
            this.hobbyId = hobbyId;
            this.hobbyName = hobbyName;
        }
    }
}
