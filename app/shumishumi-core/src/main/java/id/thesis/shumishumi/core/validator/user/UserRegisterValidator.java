package id.thesis.shumishumi.core.validator.user;

import id.thesis.shumishumi.common.service.DictionaryService;
import id.thesis.shumishumi.common.util.ParamChecker;
import id.thesis.shumishumi.core.validator.BaseValidator;
import id.thesis.shumishumi.facade.model.constant.CommonConst;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.user.UserRegisterRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class UserRegisterValidator implements BaseValidator {

    @Autowired
    private DictionaryService dictionaryService;

    @Override
    public void validate(BaseRequest baseRequest) {
        ParamChecker.isNotNull(baseRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(baseRequest instanceof UserRegisterRequest, "request", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        UserRegisterRequest request = (UserRegisterRequest) baseRequest;
        // check email
        ParamChecker.isNotEmpty(request.getEmail(), "email", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(request.getEmail(),
                "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$",
                "email", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        // check username
        ParamChecker.isNotEmpty(request.getUsername(), "username", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        // gender
        ParamChecker.isNotEmpty(request.getGender(), "gender", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(dictionaryService.queryByType(CommonConst.DICTIONARY_GENDER).contains(request.getGender()), "gender",
                ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        // date of birth
        ParamChecker.isNotNull(request.getDateOfBirth(), "date of birth", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        checkBirthYear(request.getDateOfBirth());

        // check phonenumber
        ParamChecker.isNotEmpty(request.getPhoneNumber(), "phone number", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(request.getPhoneNumber(), "^[0-9]*$", "phoneNumber", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        // check password
        ParamChecker.isNotEmpty(request.getPassword(), "password", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(request.getPassword(), "^(?:[\\d,\\/().]*[a-zA-Z][a-zA-Z\\d,\\/().]*)?$", "password", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        // confirm password
        ParamChecker.isNotEmpty(request.getConfirmPassword(), "confirm password", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        ParamChecker.isExpected(StringUtils.equals(request.getPassword(), request.getConfirmPassword()), "confirm password", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }

    private void checkBirthYear(Date dateOfBirth) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateOfBirth);

        int yearBirth = calendar.get(Calendar.YEAR);
        int yearCurrent = LocalDate.now().getYear();

        ParamChecker.isExpected((yearCurrent - yearBirth) >= 18, "date of birth", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
    }
}
