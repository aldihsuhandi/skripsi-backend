package id.thesis.shumishumi.core.validator.item;

import id.thesis.shumishumi.common.service.DictionaryService;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.context.SortingContext;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.item.QueryItemRequest;
import org.springframework.beans.factory.annotation.Autowired;

public class QueryItemValidator implements BaseValidator {

    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof QueryItemRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        QueryItemRequest request = (QueryItemRequest) baseRequest;
        sortingValidation(request.getSortingContext());
    }

    private void sortingValidation(SortingContext sortingContext) {
        ParamChecker.isNotNull(sortingContext, "sorting context", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(dictionaryService.queryByType(CommonConst.DICTIONARY_ITEM_SORTING)
                .contains(sortingContext.getSorting()), "sorting", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(dictionaryService.queryByType(CommonConst.DICTIONARY_SORTING_TYPE)
                .contains(sortingContext.getSortingType()), "sorting type", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
