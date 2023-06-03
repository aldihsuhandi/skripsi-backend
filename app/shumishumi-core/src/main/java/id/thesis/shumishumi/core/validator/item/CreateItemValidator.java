/**
 *
 */
package id.thesis.shumishumi.core.validator.item;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.InterestLevelEnum;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.item.CreateItemRequest;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: CreateItemValidator.java, v 0.1 2023‐01‐16 4:27 PM Aldih Suhandi Exp $$
 */
public class CreateItemValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {

        ParamChecker.isNotNull(baseRequest, "baseRequest", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof CreateItemRequest, "baseRequest", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        CreateItemRequest request = (CreateItemRequest) baseRequest;

        ParamChecker.isNotEmpty(request.getItemName(), "Name", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ParamChecker.isNotNull(request.getItemPrice(), "Price", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(request.getItemPrice() > 100, "Price", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ParamChecker.isNotEmpty(request.getItemImages(), "images", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ParamChecker.isNotNull(request.getItemQuantity(), "Quantity", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(request.getItemQuantity() > 0, "quantity", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ParamChecker.isNotEmpty(request.getItemImages(), "item images", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(request.getItemImages().size() <= 10, "item images", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ParamChecker.isNotNull(InterestLevelEnum.findByName(request.getMerchantInterestLevel()),
                "merchantInterestLevel", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ParamChecker.isNotEmpty(request.getHobbyName(), "hobbyName", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        ParamChecker.isNotEmpty(request.getCategoryName(), "categoryName", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
