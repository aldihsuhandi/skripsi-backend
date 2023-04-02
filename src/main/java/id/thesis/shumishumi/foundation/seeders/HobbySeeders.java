package id.thesis.shumishumi.foundation.seeders;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.core.service.HobbyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import java.util.ArrayList;
import java.util.List;

@Priority(1)
@Component
public class HobbySeeders extends BaseSeeders {
    @Override
    String setTableName() {
        return DatabaseConst.TABLE_HOBBIES;
    }

    @Override
    String setSeedersName() {
        return "hobbySeeders";
    }

    @Autowired
    private HobbyService hobbyService;

    @Override
    void seeds() {
        List<String> hobbies = new ArrayList<>();
        hobbies.add("Computer");
        hobbies.add("Keyboard");

        hobbies.forEach(hobby -> {
            try {
                hobbyService.create(hobby);
                LogUtil.info(LOGGER, String.format("hobbySeeders: successfully create hobby %s", hobby));
            } catch (Exception e) {
                LogUtil.info(LOGGER, String.format("hobbySeeders: encountered an error %s", e.getMessage()));
            }
        });
    }

    @Override
    @PostConstruct
    public void execute() {
        super.execute();
    }
}
