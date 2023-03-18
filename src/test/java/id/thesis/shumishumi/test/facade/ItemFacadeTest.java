package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.common.model.context.ItemFilterContext;
import id.thesis.shumishumi.common.model.context.ItemUpdateContext;
import id.thesis.shumishumi.common.model.enumeration.InterestLevelEnum;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.enumeration.UserRolesEnum;
import id.thesis.shumishumi.core.facade.ItemFacade;
import id.thesis.shumishumi.dalgen.model.result.HobbyDO;
import id.thesis.shumishumi.dalgen.model.result.InterestLevelDO;
import id.thesis.shumishumi.dalgen.model.result.ItemCategoryDO;
import id.thesis.shumishumi.dalgen.model.result.ItemDO;
import id.thesis.shumishumi.rest.request.item.CreateItemRequest;
import id.thesis.shumishumi.rest.request.item.ItemApprovalRequest;
import id.thesis.shumishumi.rest.request.item.QueryItemRequest;
import id.thesis.shumishumi.rest.request.item.UpdateItemRequest;
import id.thesis.shumishumi.rest.result.item.CreateItemResult;
import id.thesis.shumishumi.rest.result.item.ItemApprovalResult;
import id.thesis.shumishumi.rest.result.item.QueryItemResult;
import id.thesis.shumishumi.rest.result.item.UpdateItemResult;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ItemFacadeTest extends FacadeTestBase {
    @Autowired
    private ItemFacade itemFacade;

    @BeforeEach
    public void before() {
        Mockito.when(hobbyDAO.queryByName(Mockito.any())).thenReturn(mockHobbyDO());
        Mockito.when(itemCategoryDAO.queryByName(Mockito.any())).thenReturn(mockCategoryDO());
        Mockito.when(interestLevelDAO.queryByName(Mockito.any())).thenReturn(mockInterestLevelDO());

        Mockito.when(hobbyDAO.queryById(Mockito.any())).thenReturn(mockHobbyDO());
        Mockito.when(itemCategoryDAO.queryById(Mockito.any())).thenReturn(mockCategoryDO());
        Mockito.when(interestLevelDAO.queryById(Mockito.any())).thenReturn(mockInterestLevelDO());
    }

    @Test
    public void createItemTest_SUCCESS() {
        CreateItemRequest request = new CreateItemRequest();
        request.setItemName("item name");
        request.setItemPrice(10000L);
        request.setItemDescription("item description");
        request.setItemQuantity(100);
        request.setMerchantInterestLevel(InterestLevelEnum.BEGINNER.getLevel());
        request.setHobbyName("hobby name");
        request.setCategoryName("category name");

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO());
        Mockito.when(userDAO.queryById(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO(UserRolesEnum.MERCHANT.getUserRoleName()));


        CreateItemResult result = itemFacade.create(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void createItemTest_FAILED_userNotMerchant() {
        CreateItemRequest request = new CreateItemRequest();
        request.setItemName("item name");
        request.setItemPrice(10000L);
        request.setItemDescription("item description");
        request.setItemQuantity(100);
        request.setMerchantInterestLevel(InterestLevelEnum.BEGINNER.getLevel());
        request.setHobbyName("hobby name");
        request.setCategoryName("category name");

        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO(UserRolesEnum.USER.getUserRoleName()));
        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO());
        Mockito.when(userDAO.queryById(Mockito.any())).thenReturn(mockUserDO("password"));


        CreateItemResult result = itemFacade.create(request);
        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.USER_ROLE_INVALID.getErrorCode());
    }

    @Test
    public void updateItemTest_SUCCESS() {
        UpdateItemRequest request = new UpdateItemRequest();
        ItemUpdateContext updateContext = new ItemUpdateContext();
        updateContext.setItemQuantity(10);
        updateContext.setItemDescription("item description");

        request.setItemId("itemId");
        request.setItemUpdateContext(updateContext);

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO());
        Mockito.when(userDAO.queryById(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO(UserRolesEnum.MERCHANT.getUserRoleName()));
        Mockito.when(itemDAO.queryById(Mockito.any())).thenReturn(mockItemDO(true));

        UpdateItemResult result = itemFacade.update(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void updateItemTest_FAILED_itemNotFound() {
        UpdateItemRequest request = new UpdateItemRequest();
        ItemUpdateContext updateContext = new ItemUpdateContext();
        updateContext.setItemQuantity(10);
        updateContext.setItemDescription("item description");

        request.setItemId("itemId");
        request.setItemUpdateContext(updateContext);

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO());
        Mockito.when(userDAO.queryById(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO(UserRolesEnum.MERCHANT.getUserRoleName()));

        UpdateItemResult result = itemFacade.update(request);
        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.ITEM_NOT_FOUND.getErrorCode());
    }

    @Test
    public void queryItemTest_SUCCESS_byIdWithSession() {
        QueryItemRequest request = new QueryItemRequest();
        ItemFilterContext filterContext = new ItemFilterContext();
        filterContext.setItemId("itemId");

        request.setItemFilterContext(filterContext);
        request.setSessionId("sessoinId");

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO());
        Mockito.when(userDAO.queryById(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO(UserRolesEnum.USER.getUserRoleName()));
        Mockito.when(itemDAO.queryById(Mockito.any())).thenReturn(mockItemDO(true));

        QueryItemResult result = itemFacade.query(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void queryItemTest_SUCCESS_queryListWithSession() {
        QueryItemRequest request = new QueryItemRequest();
        ItemFilterContext filterContext = new ItemFilterContext();
        filterContext.setItemCategory("category");

        request.setItemFilterContext(filterContext);

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO());
        Mockito.when(userDAO.queryByEmail(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO(UserRolesEnum.USER.getUserRoleName()));
        Mockito.when(itemDAO.query(Mockito.any())).thenReturn(mockItemDOList(10));

        QueryItemResult result = itemFacade.query(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void approvalItemTest_SUCCESS() {
        ItemApprovalRequest request = new ItemApprovalRequest();
        request.setItemId("itemId");
        request.setSessionId("sessionId");

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO());
        Mockito.when(userDAO.queryById(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO(UserRolesEnum.ADMIN.getUserRoleName()));
        Mockito.when(itemDAO.queryById(Mockito.any())).thenReturn(mockItemDO(false));

        ItemApprovalResult result = itemFacade.approve(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void approvalItemTest_FAILED_itemAlreadyApproved() {
        ItemApprovalRequest request = new ItemApprovalRequest();
        request.setItemId("itemId");
        request.setSessionId("sessionId");

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO());
        Mockito.when(userDAO.queryById(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO(UserRolesEnum.ADMIN.getUserRoleName()));
        Mockito.when(itemDAO.queryById(Mockito.any())).thenReturn(mockItemDO(true));

        ItemApprovalResult result = itemFacade.approve(request);
        ResultAssert.isNotSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.ITEM_ALREADY_APPROVED.getErrorCode());
    }

    private HobbyDO mockHobbyDO() {
        HobbyDO hobbyDO = new HobbyDO();
        hobbyDO.setHobbyId("hobbyid");
        hobbyDO.setHobbyName("hobby name");
        hobbyDO.setGmtCreate(new Date());
        hobbyDO.setGmtModified(new Date());

        return hobbyDO;
    }

    private ItemCategoryDO mockCategoryDO() {
        ItemCategoryDO categoryDO = new ItemCategoryDO();
        categoryDO.setCategoryId("categoryId");
        categoryDO.setCategoryName("categoryName");
        categoryDO.setGmtCreate(new Date());
        categoryDO.setGmtModified(new Date());

        return categoryDO;
    }

    private InterestLevelDO mockInterestLevelDO() {
        InterestLevelDO levelDO = new InterestLevelDO();
        levelDO.setInterestLevelName("BEGINNER");
        levelDO.setInterestLevelId("id");
        levelDO.setGmtCreate(new Date());
        levelDO.setGmtModified(new Date());

        return levelDO;
    }

    private List<ItemDO> mockItemDOList(int n) {
        List<ItemDO> itemDOS = new ArrayList<>();
        for (int i = 0; i < n; ++i) {
            itemDOS.add(mockItemDO(true));
        }

        return itemDOS;
    }

    private ItemDO mockItemDO(boolean isApproved) {
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
