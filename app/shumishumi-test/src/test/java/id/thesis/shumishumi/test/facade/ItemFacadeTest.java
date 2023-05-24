package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.facade.api.ItemFacade;
import id.thesis.shumishumi.facade.model.context.ItemFilterContext;
import id.thesis.shumishumi.facade.model.context.ItemUpdateContext;
import id.thesis.shumishumi.facade.model.context.SortingContext;
import id.thesis.shumishumi.facade.model.enumeration.InterestLevelEnum;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.enumeration.UserRolesEnum;
import id.thesis.shumishumi.facade.request.item.AutocompleteItemRequest;
import id.thesis.shumishumi.facade.request.item.CreateItemRequest;
import id.thesis.shumishumi.facade.request.item.ItemApprovalRequest;
import id.thesis.shumishumi.facade.request.item.QueryItemDetailRequest;
import id.thesis.shumishumi.facade.request.item.QueryItemRequest;
import id.thesis.shumishumi.facade.request.item.RecommendRequest;
import id.thesis.shumishumi.facade.request.item.UpdateItemRequest;
import id.thesis.shumishumi.facade.result.item.AutocompleteItemResult;
import id.thesis.shumishumi.facade.result.item.CreateItemResult;
import id.thesis.shumishumi.facade.result.item.ItemApprovalResult;
import id.thesis.shumishumi.facade.result.item.QueryItemDetailResult;
import id.thesis.shumishumi.facade.result.item.QueryItemResult;
import id.thesis.shumishumi.facade.result.item.RecommendResult;
import id.thesis.shumishumi.facade.result.item.UpdateItemResult;
import id.thesis.shumishumi.foundation.model.result.ItemWishlistDO;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

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
        request.setItemImages(Collections.singletonList("images"));
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
        request.setItemImages(Collections.singletonList("images"));
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
        QueryItemDetailRequest request = new QueryItemDetailRequest();
        request.setItemId("itemId");
        request.setSessionId("sessoinId");

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO());
        Mockito.when(userDAO.queryById(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO(UserRolesEnum.USER.getUserRoleName()));
        Mockito.when(itemDAO.queryById(Mockito.any())).thenReturn(mockItemDO(true));

        QueryItemDetailResult result = itemFacade.queryDetail(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void queryItemTest_SUCCESS_withWishlist() {
        QueryItemDetailRequest request = new QueryItemDetailRequest();
        request.setItemId("itemId");
        request.setSessionId("sessionId");

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO());
        Mockito.when(userDAO.queryById(Mockito.any())).thenReturn(mockUserDO("password"));
        Mockito.when(roleDAO.queryById(Mockito.any())).thenReturn(mockRoleDO(UserRolesEnum.USER.getUserRoleName()));
        Mockito.when(itemDAO.queryById(Mockito.any())).thenReturn(mockItemDO(true));

        ItemWishlistDO wishlistDO = new ItemWishlistDO();
        Mockito.when(itemWishlistDAO.findByUserIdAndItemId(Mockito.any(),
                Mockito.any())).thenReturn(wishlistDO);

        QueryItemDetailResult result = itemFacade.queryDetail(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void queryItemTest_SUCCESS_queryListWithSession() {
        QueryItemRequest request = new QueryItemRequest();
        ItemFilterContext filterContext = new ItemFilterContext();
        filterContext.setItemCategory("category");

        SortingContext sortingContext = new SortingContext();
        sortingContext.setSorting("sorting");
        sortingContext.setSortingType("sorting type");

        request.setItemFilterContext(filterContext);
        request.setSortingContext(sortingContext);

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

    @Test
    public void recommendTest_SUCCESS() {
        RecommendRequest request = new RecommendRequest();
        request.setSessionId("sessionId");

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO());

        RecommendResult result = itemFacade.recommend(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
    }

    @Test
    public void autocompleteTest_SUCCESS() {
        AutocompleteItemRequest request = new AutocompleteItemRequest();
        request.setAutocomplete("item");

        AutocompleteItemResult result = itemFacade.autocomplete(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
    }

}
