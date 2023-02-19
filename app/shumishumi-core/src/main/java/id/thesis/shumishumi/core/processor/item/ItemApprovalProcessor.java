package id.thesis.shumishumi.core.processor.item;

import id.thesis.shumishumi.common.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.common.model.enumeration.UserRolesEnum;
import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.common.model.viewobject.SessionVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.service.ItemService;
import id.thesis.shumishumi.core.service.SessionService;
import id.thesis.shumishumi.core.service.UserService;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.item.ItemApprovalRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import org.springframework.beans.factory.annotation.Autowired;

public class ItemApprovalProcessor implements BaseProcessor {

    @Autowired
    private ItemService itemService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserService userService;

    @Override
    public void doProcess(BaseResult result, BaseRequest request) {
        ItemApprovalRequest approvalRequest = (ItemApprovalRequest) request;

        SessionVO sessionVO = sessionService.query(approvalRequest.getSessionId());
        UserVO userVO = userService.queryById(sessionVO.getUserId(), true);

        AssertUtil.isNotNull(userVO, "user not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(userVO.isDeleted(), false, "user not found", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(userVO.isActive(), true, "user is not active", ShumishumiErrorCodeEnum.USER_NOT_ACTIVE);
        AssertUtil.isExpected(userVO.getRoleVO().getRoleName(), UserRolesEnum.ADMIN.getUserRoleName(), "user is not an admin", ShumishumiErrorCodeEnum.USER_ROLE_INVALID);

        ItemVO itemVO = itemService.queryById(approvalRequest.getItemId(), true);
        AssertUtil.isNotNull(itemVO, "item not found", ShumishumiErrorCodeEnum.ITEM_NOT_FOUND);
        AssertUtil.isExpected(itemVO.isDeleted(), false, "item not found", ShumishumiErrorCodeEnum.ITEM_NOT_FOUND);
        AssertUtil.isExpected(itemVO.isApproved(), false, "item already approved", ShumishumiErrorCodeEnum.ITEM_ALREADY_APPROVED);

        itemService.approveItem(itemVO.getItemId());
    }
}
