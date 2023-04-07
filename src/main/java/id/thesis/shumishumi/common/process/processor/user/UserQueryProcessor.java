/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.common.process.processor.user;

import id.thesis.shumishumi.common.util.constant.DatabaseConst;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.common.process.processor.BaseProcessor;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.core.request.BaseRequest;
import id.thesis.shumishumi.core.request.user.UserQueryRequest;
import id.thesis.shumishumi.core.result.BaseResult;
import id.thesis.shumishumi.core.result.user.UserQueryResult;
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

        userVO.setPassword(FunctionUtil.hideString(userVO.getPassword()));

        queryResult.setUserInfo(userVO);
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
