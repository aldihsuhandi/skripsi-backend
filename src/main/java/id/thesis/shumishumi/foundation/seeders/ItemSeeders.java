package id.thesis.shumishumi.foundation.seeders;

import id.thesis.shumishumi.common.util.constant.DatabaseConst;
import id.thesis.shumishumi.common.model.enumeration.InterestLevelEnum;
import id.thesis.shumishumi.common.model.request.item.CreateItemInnerRequest;
import id.thesis.shumishumi.common.model.viewobject.HobbyVO;
import id.thesis.shumishumi.common.model.viewobject.InterestLevelVO;
import id.thesis.shumishumi.common.model.viewobject.ItemCategoryVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.common.util.FunctionUtil;
import id.thesis.shumishumi.common.util.LogUtil;
import id.thesis.shumishumi.core.service.HobbyService;
import id.thesis.shumishumi.core.service.InterestLevelService;
import id.thesis.shumishumi.core.service.ItemCategoryService;
import id.thesis.shumishumi.core.service.ItemService;
import id.thesis.shumishumi.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;
import java.util.ArrayList;
import java.util.List;

@Priority(10)
@Component
@Profile("!test")
public class ItemSeeders extends BaseSeeders {
    @Override
    String setTableName() {
        return DatabaseConst.TABLE_ITEM;
    }

    @Override
    String setSeedersName() {
        return "itemSeeders";
    }

    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemCategoryService itemCategoryService;

    @Autowired
    private HobbyService hobbyService;

    @Autowired
    private UserService userService;

    @Autowired
    private InterestLevelService interestLevelService;

    private void createItem(Request request) {
        HobbyVO hobbyVO = hobbyService.query(request.hobby, DatabaseConst.HOBBY_NAME);
        InterestLevelVO interestLevelVO = interestLevelService.query(request.interesetLevel, DatabaseConst.INTEREST_LEVEL_NAME);
        ItemCategoryVO categoryVO = itemCategoryService.query(request.category, DatabaseConst.CATEGORY_NAME);
        UserVO userVO = userService.queryByEmail(request.merchantEmail, true);
        String itemId = FunctionUtil.generateUUID();

        CreateItemInnerRequest create = new CreateItemInnerRequest();
        create.setItemId(itemId);
        create.setItemName(request.itemName);
        create.setItemPrice(request.itemPrice);
        create.setItemDescription(request.itemDesc);
        create.setItemQuantity(100);
        create.setCategoryId(categoryVO.getCategoryId());
        create.setHobbyId(hobbyVO.getHobbyId());
        create.setMerchantLevelId(interestLevelVO.getInterestLevelId());
        create.setMerchantId(userVO.getUserId());

        itemService.create(create);
    }

    @Override
    void seeds() {
        List<Request> requests = new ArrayList<>();
        // hobby computer
        // CPU
        requests.add(new Request("Ryzen 5 3600", 2100000L, "this is a cpu", "Computer",
                "CPU", "BackspaceComputer@mockUser.com", InterestLevelEnum.BEGINNER.getLevel()));
        requests.add(new Request("Intel I7 12700x", 5700000L, "this is a cpu", "Computer",
                "CPU", "BackspaceComputer@mockUser.com", InterestLevelEnum.INTERMEDIATE.getLevel()));
        // GPU
        requests.add(new Request("AMD RX 6900 XT", 12700000L, "this is a gpu", "Computer",
                "GPU", "BackspaceComputer@mockUser.com", InterestLevelEnum.INTERMEDIATE.getLevel()));

        requests.forEach(request -> {
            try {
                createItem(request);
                LogUtil.info(LOGGER, String.format("itemSeeders: successfully create item %s", request.itemName));
            } catch (Exception e) {
                LogUtil.exception(e.getMessage(), e);
                LogUtil.info(LOGGER, String.format("itemSeeders: encountered an error %s", e.getMessage()));
            }
        });
    }

    @Override
    @PostConstruct
    public void execute() {
        super.execute();
    }

    private class Request {
        String itemName;
        Long itemPrice;
        String itemDesc;
        String hobby;
        String category;
        String merchantEmail;

        public Request(String itemName, Long itemPrice, String itemDesc, String hobby, String category, String merchantEmail, String interesetLevel) {
            this.itemName = itemName;
            this.itemPrice = itemPrice;
            this.itemDesc = itemDesc;
            this.hobby = hobby;
            this.category = category;
            this.merchantEmail = merchantEmail;
            this.interesetLevel = interesetLevel;
        }

        String interesetLevel;
    }
}
