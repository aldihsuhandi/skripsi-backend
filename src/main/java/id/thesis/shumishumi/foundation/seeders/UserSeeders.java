package id.thesis.shumishumi.foundation.seeders;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.model.context.UserUpdateContext;
import id.thesis.shumishumi.common.model.datastructure.Pair;
import id.thesis.shumishumi.common.model.enumeration.UserRolesEnum;
import id.thesis.shumishumi.common.model.request.user.RoleChangeInnerRequest;
import id.thesis.shumishumi.common.model.request.user.UserCreateInnerRequest;
import id.thesis.shumishumi.common.model.request.user.UserUpdateInnerRequest;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.core.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import java.util.ArrayList;
import java.util.List;

@Priority(1)
@Component
public class UserSeeders extends BaseSeeders {
    @Autowired
    private UserService userService;

    @Override
    String setTableName() {
        return DatabaseConst.TABLE_USER;
    }

    @Override
    String setSeedersName() {
        return "userSeeders";
    }

    private void createMerchant(String username, String password) {
        String email = String.format("%s@mockUser.com", username);
        String userId = FunctionUtil.generateUUID();
        String phoneNumber = "08" + RandomStringUtils.random(10, true, false);
        password = FunctionUtil.hashPassword(password);

        // register
        UserCreateInnerRequest create = new UserCreateInnerRequest();
        create.setUserId(userId);
        create.setUsername(username);
        create.setPhoneNumber(phoneNumber);
        create.setEmail(email);
        create.setPassword(password);

        userService.register(create);

        // query user
        UserVO userVO = userService.queryByEmail(email, true);

        // activate user
        UserUpdateInnerRequest update = new UserUpdateInnerRequest();
        UserUpdateContext updateContext = new UserUpdateContext();
        updateContext.setIsActive(true);
        FunctionUtil.fillEmptyUpdateContext(updateContext, userVO);

        update.setUserId(userId);
        update.setUserUpdateContext(updateContext);

        userService.update(update);


        // change to merchant role
        RoleChangeInnerRequest changeRole = new RoleChangeInnerRequest();
        changeRole.setUserId(userId);
        changeRole.setUserRole(UserRolesEnum.MERCHANT.getUserRoleId());

        userService.roleChange(changeRole);
    }

    @Override
    void seeds() {
        List<Pair<String, String>> merchants = new ArrayList<>();
        merchants.add(new Pair<>("Mechanical Keyboard", "keyboard1234"));
        merchants.add(new Pair<>("BackspaceComputer", "Computer is love Computer is Live"));


        // create merchant
        merchants.forEach(merchant -> {
            try {
                createMerchant(merchant.getFirst(), merchant.getSecond());
                LogUtil.info(LOGGER, String.format("userSeeders: successfully create user %s", merchant.getFirst()));
            } catch (Exception e) {
                LogUtil.exception(e.getMessage(), e);
                LogUtil.info(LOGGER, String.format("userSeeders: encountered an error %s", e.getMessage()));
            }
        });
    }

    @Override
    @PostConstruct
    public void execute() {
        super.execute();
    }
}
