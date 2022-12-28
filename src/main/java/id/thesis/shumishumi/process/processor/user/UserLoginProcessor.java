package id.thesis.shumishumi.process.processor.user;

import id.thesis.shumishumi.common.exception.ShumishumiException;
import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.request.session.SessionCreateInnerRequest;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.process.processor.BaseProcessor;
import id.thesis.shumishumi.rest.request.BaseRequest;
import id.thesis.shumishumi.rest.request.user.UserLoginRequest;
import id.thesis.shumishumi.rest.result.BaseResult;
import id.thesis.shumishumi.rest.result.user.UserLoginResult;
import org.springframework.beans.factory.annotation.Autowired;

public class UserLoginProcessor implements BaseProcessor {

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult result, BaseRequest request) throws ShumishumiException {
        UserLoginRequest loginRequest = (UserLoginRequest) request;
        UserLoginResult loginResult = (UserLoginResult) result;

        UserVO userVO = userService.queryByEmail(loginRequest.getEmail(), true);
        AssertUtil.isNotNull(userVO, "User not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);

        AssertUtil.isExpected(FunctionUtil.verifyHash(loginRequest.getPassword(), userVO.getPassword()),
                "password doesn't match", ShumishumiErrorCodeEnum.AUTHENTICATION_FAILED);

        String sessionId = sessionService.create(composeRequest(userVO.getUserId(), loginRequest.isRemembered()));
        loginResult.setSessionId(sessionId);
    }

    private SessionCreateInnerRequest composeRequest(String userId, boolean isRemembered) {
        SessionCreateInnerRequest request = new SessionCreateInnerRequest();
        request.setUserId(userId);
        request.setRemembered(isRemembered);

        return request;
    }
}
