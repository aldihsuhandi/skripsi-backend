/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.processor.user;

import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.converter.SummaryConverter;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.user.UserQueryRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.user.UserQueryResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserQueryProcessor.java, v 0.1 2022‐12‐28 9:25 AM Aldih Suhandi Exp $$
 */
public class UserQueryProcessor implements BaseProcessor {

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        UserQueryRequest queryRequest = (UserQueryRequest) baseRequest;
        UserQueryResult queryResult = (UserQueryResult) baseResult;
        UserVO userVO = query(queryRequest.getKey(), queryRequest.getIdentifier());
        AssertUtil.isNotNull(userVO, "userVO", ShumishumiErrorCodeEnum.USER_NOT_FOUND);

        queryResult.setUserInfo(SummaryConverter.toSummary(userVO));
    }

    private UserVO query(String key, String identifier) {
        if (DatabaseConst.PHONE_NUMBER.equals(identifier)) {
            return userService.queryByPhoneNumber(key, true);
        }
        if (DatabaseConst.EMAIL.equals(identifier)) {
            return userService.queryByEmail(key, true);
        }

        return userService.queryById(key, true);
    }
}
