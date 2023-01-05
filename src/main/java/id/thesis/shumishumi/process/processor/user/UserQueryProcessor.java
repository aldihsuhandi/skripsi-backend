/**
 * Dana.id
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.process.processor.user;

import id.thesis.shumishumi.common.constant.DatabaseConst;
import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.process.processor.BaseProcessor;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.user.UserQueryRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import id.thesis.shumishumi.rest.result.user.UserQueryResult;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author Aldih Suhandi (i-aldih.suhandi@dana.id)
 * @version $Id: UserQueryProcessor.java, v 0.1 2022‐12‐28 9:25 AM Aldih Suhandi Exp $$
 */
public class UserQueryProcessor implements BaseProcessor {

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult result, BaseRequest request) throws ShumishumiException {
        UserQueryRequest queryRequest = (UserQueryRequest) request;
        UserQueryResult queryResult = (UserQueryResult) result;
        queryResult.setUserVO(query(queryRequest.getKey(), queryRequest.getIdentifier()));
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