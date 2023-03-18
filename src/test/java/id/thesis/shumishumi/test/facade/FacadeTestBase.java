package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.common.model.enumeration.UserRolesEnum;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.fetch.ItemFetchService;
import id.thesis.shumishumi.core.fetch.UserFetchService;
import id.thesis.shumishumi.dalgen.model.result.ActivityDO;
import id.thesis.shumishumi.dalgen.model.result.RoleDO;
import id.thesis.shumishumi.dalgen.model.result.SessionDO;
import id.thesis.shumishumi.dalgen.model.result.UserDO;
import id.thesis.shumishumi.dalgen.service.ClientDAO;
import id.thesis.shumishumi.dalgen.service.ContentDAO;
import id.thesis.shumishumi.dalgen.service.CrowdDAO;
import id.thesis.shumishumi.dalgen.service.HobbyDAO;
import id.thesis.shumishumi.dalgen.service.InterestLevelDAO;
import id.thesis.shumishumi.dalgen.service.ItemCategoryDAO;
import id.thesis.shumishumi.dalgen.service.ItemDAO;
import id.thesis.shumishumi.dalgen.service.ItemImageDAO;
import id.thesis.shumishumi.dalgen.service.OtpDAO;
import id.thesis.shumishumi.dalgen.service.RoleDAO;
import id.thesis.shumishumi.dalgen.service.SessionDAO;
import id.thesis.shumishumi.dalgen.service.UserDAO;
import id.thesis.shumishumi.test.TestBase;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Date;

public class FacadeTestBase extends TestBase {
    @MockBean
    protected UserDAO userDAO;
    @MockBean
    protected ContentDAO contentDAO;
    @MockBean
    protected OtpDAO otpDAO;
    @MockBean
    protected SessionDAO sessionDAO;
    @MockBean
    protected RoleDAO roleDAO;
    @MockBean
    protected ClientDAO clientDAO;
    @MockBean
    protected ItemDAO itemDAO;
    @MockBean
    protected HobbyDAO hobbyDAO;
    @MockBean
    protected InterestLevelDAO interestLevelDAO;
    @MockBean
    protected ItemCategoryDAO itemCategoryDAO;
    @MockBean
    protected ItemImageDAO itemImageDAO;
    @MockBean
    protected CrowdDAO crowdDAO;

    @Autowired
    protected UserFetchService userFetchService;

    @Autowired
    protected ItemFetchService itemFetchService;

    @AfterEach
    public void cleanUp() {
        itemFetchService.clearCache();
        userFetchService.clearCache();
    }

    protected SessionDO mockSessionDO() {
        SessionDO sessionDO = new SessionDO();
        sessionDO.setUserId("userId");
        sessionDO.setRemembered(true);
        sessionDO.setActive(true);
        sessionDO.setSessionDt(DateUtils.addHours(new Date(), 2));

        return sessionDO;
    }


    protected UserDO mockUserDO(String password) {
        UserDO user = new UserDO();
        String hashPassword = FunctionUtil.hashPassword(password);
        user.setUserId("userId");
        user.setEmail("email");
        user.setActive(true);
        user.setPassword(hashPassword);

        return user;
    }

    protected UserDO mockUserDO(String password, boolean isActive) {
        UserDO user = new UserDO();
        String hashPassword = FunctionUtil.hashPassword(password);
        user.setUserId("userId");
        user.setEmail("email");
        user.setActive(isActive);
        user.setPassword(hashPassword);

        return user;
    }

    protected RoleDO mockRoleDO() {
        return mockRoleDO(UserRolesEnum.USER.getUserRoleName());
    }

    protected RoleDO mockRoleDO(String roleName) {
        RoleDO roleDO = new RoleDO();
        roleDO.setRoleId(UserRolesEnum.valueOf(roleName).getUserRoleId());
        roleDO.setRoleName(UserRolesEnum.valueOf(roleName).getUserRoleName());
        roleDO.setGmtCreate(new Date());
        roleDO.setGmtModified(new Date());

        return roleDO;
    }

    protected ActivityDO mockActivityDO() {
        ActivityDO activityDO = new ActivityDO();
        activityDO.setActivityId("activityId");
        activityDO.setActivityName("activityName");
        activityDO.setGmtCreate(new Date());
        activityDO.setGmtModified(new Date());

        return activityDO;
    }

}