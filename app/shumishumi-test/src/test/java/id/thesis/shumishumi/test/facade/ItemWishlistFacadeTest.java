package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.facade.api.ItemWishlistFacade;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.item.wishlist.AddWishlistRequest;
import id.thesis.shumishumi.facade.request.item.wishlist.QueryWishlistRequest;
import id.thesis.shumishumi.facade.request.item.wishlist.RemoveWishlistRequest;
import id.thesis.shumishumi.facade.result.item.wishlist.AddWishlistResult;
import id.thesis.shumishumi.facade.result.item.wishlist.QueryWishlistResult;
import id.thesis.shumishumi.facade.result.item.wishlist.RemoveWishlistResult;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemWishlistFacadeTest extends FacadeTestBase {
    @BeforeEach
    public void before() {
        Mockito.when(hobbyDAO.queryByName(Mockito.any())).thenReturn(mockHobbyDO());
        Mockito.when(itemCategoryDAO.queryByName(Mockito.any())).thenReturn(mockCategoryDO());
        Mockito.when(interestLevelDAO.queryByName(Mockito.any())).thenReturn(mockInterestLevelDO());

        Mockito.when(hobbyDAO.queryById(Mockito.any())).thenReturn(mockHobbyDO());
        Mockito.when(itemCategoryDAO.queryById(Mockito.any())).thenReturn(mockCategoryDO());
        Mockito.when(interestLevelDAO.queryById(Mockito.any())).thenReturn(mockInterestLevelDO());

        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO());
        Mockito.when(itemDAO.queryById(Mockito.any())).thenReturn(mockItemDO(true));
        Mockito.when(userDAO.queryById(Mockito.any())).thenReturn(mockUserDO("this is a password", true, false));
    }

    @Autowired
    private ItemWishlistFacade itemWishlistFacade;

    @Test
    public void addWishlist_SUCCESS() {
        AddWishlistRequest request = new AddWishlistRequest();
        request.setItemId("itemId");
        request.setSessionId("sessionId");

        AddWishlistResult result = itemWishlistFacade.add(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void addWishlist_FAILED_paramIllegal() {
        AddWishlistRequest request = new AddWishlistRequest();
        request.setSessionId("sessionId");

        AddWishlistResult result = itemWishlistFacade.add(request);
        ResultAssert.isSuccess(!result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), ShumishumiErrorCodeEnum.PARAM_ILLEGAL.getErrorCode());
    }

    @Test
    public void removeWishlist_SUCCESS() {
        RemoveWishlistRequest request = new RemoveWishlistRequest();
        request.setItemId("itemId");
        request.setSessionId("sessionId");

        RemoveWishlistResult result = itemWishlistFacade.remove(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }

    @Test
    public void queryWishlist_SUCCESS() {
        QueryWishlistRequest request = new QueryWishlistRequest();
        request.setSessionId("sessionId");

        QueryWishlistResult result = itemWishlistFacade.query(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(), "SUCCESS");
    }
}
