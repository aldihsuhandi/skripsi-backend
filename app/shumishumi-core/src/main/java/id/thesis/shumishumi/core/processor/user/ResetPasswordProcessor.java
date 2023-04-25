package id.thesis.shumishumi.core.processor.user;

import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.converter.UserRequestConverter;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.context.UserUpdateContext;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.user.UserResetPasswordRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;

public class ResetPasswordProcessor implements BaseProcessor {

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        UserResetPasswordRequest forgotPasswordRequest = (UserResetPasswordRequest) baseRequest;
        UserVO userVO = userService.queryByEmail(forgotPasswordRequest.getEmail(), true);

        validateUser(userVO);

        UserUpdateContext updateContext = new UserUpdateContext();
        updateContext.setPassword(userVO.getPassword());

        FunctionUtil.fillEmptyUpdateContext(updateContext, userVO);

        userService.update(UserRequestConverter.toInnerRequest(userVO.getUserId(), updateContext));
        userService.refreshCache(new ArrayList<>(Collections.singletonList(userVO.getUserId())), false);
    }

    private void validateUser(UserVO userVO) {
        AssertUtil.isNotNull(userVO, "user not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(!userVO.isDeleted(), "user not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
    }
}
