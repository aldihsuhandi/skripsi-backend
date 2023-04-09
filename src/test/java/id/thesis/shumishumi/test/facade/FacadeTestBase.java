package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.common.model.enumeration.InterestLevelEnum;
import id.thesis.shumishumi.common.model.enumeration.UserRolesEnum;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.fetch.ItemFetchService;
import id.thesis.shumishumi.core.fetch.UserFetchService;
import id.thesis.shumishumi.foundation.dalgen.model.result.ActivityDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.HobbyDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.InterestLevelDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.ItemCategoryDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.ItemDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.RoleDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.SessionDO;
import id.thesis.shumishumi.foundation.dalgen.model.result.UserDO;
import id.thesis.shumishumi.foundation.dalgen.service.ClientDAO;
import id.thesis.shumishumi.foundation.dalgen.service.ContentDAO;
import id.thesis.shumishumi.foundation.dalgen.service.CrowdDAO;
import id.thesis.shumishumi.foundation.dalgen.service.HobbyDAO;
import id.thesis.shumishumi.foundation.dalgen.service.ImageDAO;
import id.thesis.shumishumi.foundation.dalgen.service.InterestLevelDAO;
import id.thesis.shumishumi.foundation.dalgen.service.ItemCategoryDAO;
import id.thesis.shumishumi.foundation.dalgen.service.ItemDAO;
import id.thesis.shumishumi.foundation.dalgen.service.ItemWishlistDAO;
import id.thesis.shumishumi.foundation.dalgen.service.OtpDAO;
import id.thesis.shumishumi.foundation.dalgen.service.RoleDAO;
import id.thesis.shumishumi.foundation.dalgen.service.SessionDAO;
import id.thesis.shumishumi.foundation.dalgen.service.UserDAO;
import id.thesis.shumishumi.test.TestBase;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    protected ItemWishlistDAO itemWishlistDAO;
    @MockBean
    protected HobbyDAO hobbyDAO;
    @MockBean
    protected InterestLevelDAO interestLevelDAO;
    @MockBean
    protected ItemCategoryDAO itemCategoryDAO;
    @MockBean
    protected CrowdDAO crowdDAO;
    @MockBean
    protected ImageDAO imageDAO;

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
        return mockUserDO(password, true, false);
    }

    protected UserDO mockUserDO(String password, boolean isActive, boolean isDeleted) {
        UserDO user = new UserDO();
        String hashPassword = FunctionUtil.hashPassword(password);
        user.setUserId("userId");
        user.setEmail("email");
        user.setActive(isActive);
        user.setDeleted(isDeleted);
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

    protected HobbyDO mockHobbyDO() {
        HobbyDO hobbyDO = new HobbyDO();
        hobbyDO.setHobbyId("hobbyid");
        hobbyDO.setHobbyName("hobby name");
        hobbyDO.setGmtCreate(new Date());
        hobbyDO.setGmtModified(new Date());

        return hobbyDO;
    }

    protected ItemCategoryDO mockCategoryDO() {
        ItemCategoryDO categoryDO = new ItemCategoryDO();
        categoryDO.setCategoryId("categoryId");
        categoryDO.setCategoryName("categoryName");
        categoryDO.setGmtCreate(new Date());
        categoryDO.setGmtModified(new Date());

        return categoryDO;
    }

    protected InterestLevelDO mockInterestLevelDO() {
        InterestLevelDO levelDO = new InterestLevelDO();
        levelDO.setInterestLevelName("BEGINNER");
        levelDO.setInterestLevelId("id");
        levelDO.setGmtCreate(new Date());
        levelDO.setGmtModified(new Date());

        return levelDO;
    }

    protected List<ItemDO> mockItemDOList(int n) {
        List<ItemDO> itemDOS = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            itemDOS.add(mockItemDO(true));
        }

        return itemDOS;
    }

    protected ItemDO mockItemDO(boolean isApproved) {
        ItemDO itemDO = new ItemDO();
        itemDO.setItemId("itemId");
        itemDO.setItemName("itemName");
        itemDO.setItemPrice(10000L);
        itemDO.setItemDescription("item description");
        itemDO.setMerchantId("userId");
        itemDO.setHobbyId("hobbyId");
        itemDO.setCategoryId("categoryId");
        itemDO.setMerchantLevelId(InterestLevelEnum.BEGINNER.getLevel());
        itemDO.setItemQuantity(100);
        itemDO.setDeleted(false);
        itemDO.setApproved(isApproved);
        itemDO.setGmtCreate(new Date());
        itemDO.setGmtModified(new Date());

        return itemDO;
    }

}