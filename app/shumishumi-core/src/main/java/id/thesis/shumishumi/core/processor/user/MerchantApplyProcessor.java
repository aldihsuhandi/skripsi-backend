package id.thesis.shumishumi.core.processor.user;

import id.thesis.shumishumi.common.model.request.user.RoleChangeInnerRequest;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.enumeration.UserRolesEnum;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.user.MerchantApplyRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.Collections;

public class MerchantApplyProcessor implements BaseProcessor {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        MerchantApplyRequest request = (MerchantApplyRequest) baseRequest;

        String userId = sessionService.query(request.getSessionId()).getUserId();
        UserVO userVO = userService.queryById(userId, true);

        AssertUtil.isExpected(!StringUtils.equals(userVO.getRoleVO().getRoleName(),
                UserRolesEnum.MERCHANT.getUserRoleName()), "user role is not expected", ShumishumiErrorCodeEnum.USER_ROLE_INVALID);
        AssertUtil.isExpected(!CollectionUtils.isEmpty(userVO.getLocation()),
                "you need to fill out your address", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        RoleChangeInnerRequest innerRequest = new RoleChangeInnerRequest();
        innerRequest.setUserId(userId);
        innerRequest.setUserRole(UserRolesEnum.MERCHANT.getUserRoleId());

        userService.roleChange(innerRequest);
        userService.refreshCache(Collections.singletonList(userId), false);
    }
}
