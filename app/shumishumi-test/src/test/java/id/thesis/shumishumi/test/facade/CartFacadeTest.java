package id.thesis.shumishumi.test.facade;

import id.thesis.shumishumi.facade.api.CartFacade;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.cart.CartAddRequest;
import id.thesis.shumishumi.facade.request.cart.CartCalculateRequest;
import id.thesis.shumishumi.facade.request.cart.CartQueryRequest;
import id.thesis.shumishumi.facade.request.cart.CartUpdateRequest;
import id.thesis.shumishumi.facade.result.cart.CartAddResult;
import id.thesis.shumishumi.facade.result.cart.CartCalculateResult;
import id.thesis.shumishumi.facade.result.cart.CartQueryResult;
import id.thesis.shumishumi.facade.result.cart.CartUpdateResult;
import id.thesis.shumishumi.foundation.model.result.CartDO;
import id.thesis.shumishumi.foundation.model.result.primarykey.CartDOPK;
import id.thesis.shumishumi.test.util.ResultAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CartFacadeTest extends FacadeTestBase {
    @Autowired
    private CartFacade cartFacade;

    @BeforeEach
    public void setup() {
        Mockito.when(sessionDAO.query(Mockito.any())).thenReturn(mockSessionDO("userId"));
    }

    @Test
    public void addCartTest_SUCCESS() {
        CartAddRequest request = new CartAddRequest();
        request.setSessionId("sessionId");
        request.setItemId("itemId");
        request.setQuantity(10);

        Mockito.when(itemDAO.queryById(Mockito.any())).thenReturn(mockItemDO(true));

        CartAddResult result = cartFacade.add(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }

    @Test
    public void updateCartTest_SUCCESS() {
        CartUpdateRequest request = new CartUpdateRequest();
        request.setSessionId("sessionId");
        request.setItemId("itemID");
        request.setQuantity(19);

        Mockito.when(itemDAO.queryById(Mockito.any())).thenReturn(mockItemDO(true));
        Mockito.when(cartDAO.query(Mockito.any(), Mockito.any())).thenReturn(mockCart());

        CartUpdateResult result = cartFacade.update(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }

    @Test
    public void queryCartTest_SUCCESS() {
        CartQueryRequest request = new CartQueryRequest();
        request.setSessionId("sessionId");

        Mockito.when(itemDAO.queryById(Mockito.any())).thenReturn(mockItemDO(true));
        Mockito.when(cartDAO.queryList(Mockito.any(), Mockito.any())).thenReturn(mockCarts());
        Mockito.when(cartDAO.queryAll(Mockito.any())).thenReturn(mockCarts());

        Mockito.when(hobbyDAO.queryByName(Mockito.any())).thenReturn(mockHobbyDO());
        Mockito.when(itemCategoryDAO.queryByName(Mockito.any())).thenReturn(mockCategoryDO());
        Mockito.when(interestLevelDAO.queryByName(Mockito.any())).thenReturn(mockInterestLevelDO());

        Mockito.when(hobbyDAO.queryById(Mockito.any())).thenReturn(mockHobbyDO());
        Mockito.when(itemCategoryDAO.queryById(Mockito.any())).thenReturn(mockCategoryDO());
        Mockito.when(interestLevelDAO.queryById(Mockito.any())).thenReturn(mockInterestLevelDO());

        CartQueryResult result = cartFacade.query(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }

    @Test
    public void calculateCartTest_SUCCESS() {
        CartCalculateRequest request = new CartCalculateRequest();
        request.setSessionId("sessionId");
        Mockito.when(itemDAO.queryById(Mockito.any())).thenReturn(mockItemDO(true));
        Mockito.when(cartDAO.queryList(Mockito.any(), Mockito.any())).thenReturn(mockCarts());
        Mockito.when(cartDAO.queryAll(Mockito.any())).thenReturn(mockCarts());

        Mockito.when(hobbyDAO.queryByName(Mockito.any())).thenReturn(mockHobbyDO());
        Mockito.when(itemCategoryDAO.queryByName(Mockito.any())).thenReturn(mockCategoryDO());
        Mockito.when(interestLevelDAO.queryByName(Mockito.any())).thenReturn(mockInterestLevelDO());

        Mockito.when(hobbyDAO.queryById(Mockito.any())).thenReturn(mockHobbyDO());
        Mockito.when(itemCategoryDAO.queryById(Mockito.any())).thenReturn(mockCategoryDO());
        Mockito.when(interestLevelDAO.queryById(Mockito.any())).thenReturn(mockInterestLevelDO());

        CartCalculateResult result = cartFacade.calculatePrice(request);
        ResultAssert.isSuccess(result.getResultContext().isSuccess());
        ResultAssert.isExpected(result.getResultContext().getResultCode(),
                ShumishumiErrorCodeEnum.SUCCESS.getErrorCode());
    }

    public List<CartDO> mockCarts() {
        List<CartDO> carts = new ArrayList<>();
        for (int i = 0; i < 10; ++i) {
            carts.add(mockCart());
        }
        return carts;
    }

    public CartDO mockCart() {
        CartDO cartDO = new CartDO();
        cartDO.setPk(new CartDOPK("userId", "itemId"));
        cartDO.setQuantity(12);

        return cartDO;
    }
}
