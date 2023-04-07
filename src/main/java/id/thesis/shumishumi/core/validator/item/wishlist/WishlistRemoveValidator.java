package id.thesis.shumishumi.core.validator.item.wishlist;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.item.wishlist.RemoveWishlistRequest;

public class WishlistRemoveValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof RemoveWishlistRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        RemoveWishlistRequest request = (RemoveWishlistRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getItemId(), "itemId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
