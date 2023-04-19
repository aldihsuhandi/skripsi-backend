/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.validator.user;

import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.user.UserUpdateRequest;
import org.apache.commons.lang3.StringUtils;

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

        ParamChecker.isNotEmpty(updateRequest.getOldPassword(), "password", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        checkEmail(updateRequest.getEmail());
        checkPassword(updateRequest.getPassword(), updateRequest.getConfirmPassword());
        checkPhoneNumber(updateRequest.getPhoneNumber());
    }

    private void checkEmail(String email) {
        if (email == null || email.isEmpty()) {
            return;
        }

        ParamChecker.isExpected(email,
                "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
                "email", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }

    private void checkPassword(String password, String confirm) {
        if (password == null || password.isEmpty()) {
            return;
        }

        ParamChecker.isExpected(password.length() >= 8, "password", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(password, "^(?:[\\d,\\/().]*[a-zA-Z][a-zA-Z\\d,\\/().]*)?$", "password", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(StringUtils.equals(password, confirm), "confirm password", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }

    private void checkPhoneNumber(String phoneNumber) {
        if (phoneNumber == null || phoneNumber.isEmpty()) {
            return;
        }

        ParamChecker.isExpected(phoneNumber, "^[0-9]*$", "phoneNumber", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
