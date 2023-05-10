/**
 * Copyright (c) 2017‐2022 All Rights Reserved.
 */
package id.thesis.shumishumi.core.validator.user;

import id.thesis.shumishumi.common.service.DictionaryService;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.user.UserUpdateRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: UserUpdateValidator.java, v 0.1 2022‐12‐27 1:51 PM Aldih Suhandi Exp $$
 */
public class UserUpdateValidator implements BaseValidator {

    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof UserUpdateRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        UserUpdateRequest updateRequest = (UserUpdateRequest) baseRequest;

        ParamChecker.isNotEmpty(updateRequest.getOldPassword(), "password", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        checkEmail(updateRequest.getEmail());
        checkPassword(updateRequest.getPassword(), updateRequest.getConfirmPassword());
        checkPhoneNumber(updateRequest.getPhoneNumber());
        checkLocation(updateRequest.getLocation());
        checkGender(updateRequest.getGender());
        checkDateOfBirth(updateRequest.getDateOfBirth());
    }

    private void checkGender(String gender) {
        if (StringUtils.isEmpty(gender)) {
            return;
        }

        ParamChecker.isNotEmpty(gender, "gender", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(dictionaryService.queryByType(CommonConst.DICTIONARY_GENDER).contains(gender), "gender",
                ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

    }

    private void checkEmail(String email) {
        if (email == null || email.isEmpty()) {
            return;
        }

        ParamChecker.isExpected(email,
                "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
                "email", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }

    private void checkDateOfBirth(Date dateOfBirth) {
        if (dateOfBirth == null) {
            return;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOfBirth);

        int yearBirth = calendar.get(Calendar.YEAR);
        int yearCurrent = LocalDate.now().getYear();

        ParamChecker.isExpected((yearCurrent - yearBirth) >= 18, "date of birth", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }

    private void checkLocation(Map<String, String> location) {
        if (CollectionUtils.isEmpty(location)) {
            return;
        }

        ParamChecker.isExpected(location.containsKey(CommonConst.LOCATION_PROVINCE), CommonConst.LOCATION_PROVINCE, ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(location.containsKey(CommonConst.LOCATION_CITY), CommonConst.LOCATION_CITY, ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(location.containsKey(CommonConst.LOCATION_POST_CODE), CommonConst.LOCATION_POST_CODE, ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(location.containsKey(CommonConst.LOCATION_DETAIL), CommonConst.LOCATION_DETAIL, ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
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
