/**
 * 
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.validator.user;

import id.thesis.shumishumi.common.model.context.UserUpdateContext;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.user.UserUpdateRequest;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserUpdateValidator.java, v 0.1 2022‐12‐27 1:51 PM Aldih Suhandi Exp $$
 */
public class UserUpdateValidator implements BaseValidator {
    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof UserUpdateRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        UserUpdateRequest updateRequest = (UserUpdateRequest) baseRequest;

        ParamChecker.isNotEmpty(updateRequest.getPassword(), "password", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isNotNull(updateRequest.getUserUpdateContext(), "userUpdateContext", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        UserUpdateContext updateContext = updateRequest.getUserUpdateContext();
        checkEmail(updateContext.getEmail());
        checkPassword(updateContext.getPassword());
        checkPhoneNumber(updateContext.getPhoneNumber());
    }

    private void checkEmail(String email) {
        if (email == null || email.isEmpty()) {
            return;
        }

        ParamChecker.isExpected(email,
                "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
                "email", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }

    private void checkPassword(String password) {
        if (password == null || password.isEmpty()) {
            return;
        }

        ParamChecker.isExpected(password.length() >= 8, "password", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(password, "^(?:[\\d,\\/().]*[a-zA-Z][a-zA-Z\\d,\\/().]*)?$", "password", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }

    private void checkPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return;
        }

        ParamChecker.isExpected(phoneNumber, "^[0-9]*$", "phoneNumber", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
