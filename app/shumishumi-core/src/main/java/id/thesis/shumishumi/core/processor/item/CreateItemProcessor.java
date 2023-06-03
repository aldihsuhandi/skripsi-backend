/**
 *
 */
package id.thesis.shumishumi.core.processor.item;

import id.thesis.shumishumi.common.model.request.item.CreateItemInnerRequest;
import id.thesis.shumishumi.common.service.HobbyService;
import id.thesis.shumishumi.common.service.InterestLevelService;
import id.thesis.shumishumi.common.service.ItemCategoryService;
import id.thesis.shumishumi.common.service.ItemService;
import id.thesis.shumishumi.common.service.PostService;
import id.thesis.shumishumi.common.service.SessionService;
import id.thesis.shumishumi.common.service.UserService;
import id.thesis.shumishumi.common.util.AssertUtil;
import id.thesis.shumishumi.core.converter.ItemRequestConverter;
import id.thesis.shumishumi.core.processor.BaseProcessor;
import id.thesis.shumishumi.facade.model.constant.DatabaseConst;
import id.thesis.shumishumi.facade.model.enumeration.ShumishumiErrorCodeEnum;
import id.thesis.shumishumi.facade.model.enumeration.UserRolesEnum;
import id.thesis.shumishumi.facade.model.viewobject.HobbyVO;
import id.thesis.shumishumi.facade.model.viewobject.InterestLevelVO;
import id.thesis.shumishumi.facade.model.viewobject.ItemCategoryVO;
import id.thesis.shumishumi.facade.model.viewobject.PostVO;
import id.thesis.shumishumi.facade.model.viewobject.SessionVO;
import id.thesis.shumishumi.facade.model.viewobject.UserVO;
import id.thesis.shumishumi.facade.request.BaseRequest;
import id.thesis.shumishumi.facade.request.item.CreateItemRequest;
import id.thesis.shumishumi.facade.result.BaseResult;
import id.thesis.shumishumi.facade.result.item.CreateItemResult;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Aldih Suhandi (aldih.suhandi@binus.ac.id)
 * @version $Id: CreateItemProcessor.java, v 0.1 2023‐01‐16 4:27 PM Aldih Suhandi Exp $$
 */
public class CreateItemProcessor implements BaseProcessor {

    @Autowired
    private UserService userService;

    @Autowired
    private SessionService sessionService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCategoryService itemCategoryService;

    @Autowired
    private HobbyService hobbyService;

    @Autowired
    private InterestLevelService interestLevelService;

    @Autowired
    private PostService postService;

    @Override
    public void doProcess(BaseResult baseResult, BaseRequest baseRequest) {
        CreateItemRequest itemRequest = (CreateItemRequest) baseRequest;
        CreateItemResult itemResult = (CreateItemResult) baseResult;

        SessionVO sessionVO = sessionService.query(itemRequest.getSessionId());
        AssertUtil.isNotNull(sessionVO, "session not exist", ShumishumiErrorCodeEnum.SESSION_EXPIRED);

        UserVO merchant = userService.queryById(sessionVO.getUserId(), true);
        AssertUtil.isNotNull(merchant, "user not exist", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(merchant.isActive(), "user not active", ShumishumiErrorCodeEnum.USER_NOT_ACTIVE);
        AssertUtil.isExpected(!merchant.isDeleted(), "user not exist", ShumishumiErrorCodeEnum.USER_NOT_FOUND);
        AssertUtil.isExpected(UserRolesEnum.MERCHANT.getUserRoleName().equals(merchant.getRoleVO().getRoleName()),
                "user is not a merchant", ShumishumiErrorCodeEnum.USER_ROLE_INVALID);

        ItemCategoryVO itemCategoryVO = itemCategoryService.
                query(itemRequest.getCategoryName(), DatabaseConst.CATEGORY_NAME);

        HobbyVO hobbyVO = hobbyService.
                query(itemRequest.getHobbyName(), DatabaseConst.HOBBY_NAME);

        InterestLevelVO interestLevelVO = interestLevelService.
                query(itemRequest.getMerchantInterestLevel(), DatabaseConst.INTEREST_LEVEL_NAME);

        AssertUtil.isNotNull(itemCategoryVO, "item category not found", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        AssertUtil.isNotNull(hobbyVO, "hobby not found", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);
        AssertUtil.isNotNull(interestLevelVO, "merchant interest level not found", ShumishumiErrorCodeEnum.PARAM_ILLEGAL);

        String postId = createPostForItem(itemRequest, merchant.getUserId());

        CreateItemInnerRequest innerRequest = ItemRequestConverter.toInnerRequest(itemRequest, merchant.getUserId(),
                itemCategoryVO.getCategoryId(), hobbyVO.getHobbyId(), interestLevelVO.getInterestLevelId(), postId);

        itemService.create(innerRequest);
        itemService.refreshCache(new ArrayList<>(Collections.singletonList(innerRequest.getItemId())), false);

        itemResult.setItemId(innerRequest.getItemId());
    }

    private String createPostForItem(CreateItemRequest request, String userId) {
        List<String> tags = new ArrayList<>();
        tags.add("item");

        PostVO postVO = new PostVO();
        postVO.setTitle(request.getItemName());
        postVO.setContent(request.getItemDescription());
        postVO.setUserId(userId);
        postVO.setTags(tags);


        return postService.create(postVO);
    }
}
