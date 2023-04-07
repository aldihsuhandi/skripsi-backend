package id.thesis.shumishumi.core.validator.item.wishlist;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.item.wishlist.AddWishlistRequest;

public class WishlistAddValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof AddWishlistRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        AddWishlistRequest request = (AddWishlistRequest) baseRequest;
        ParamChecker.isNotEmpty(request.getItemId(), "itemId", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
