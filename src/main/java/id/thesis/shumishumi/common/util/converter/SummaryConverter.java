package id.thesis.shumishumi.common.util.converter;

import id.thesis.shumishumi.common.model.viewobject.ItemImageVO;
import id.thesis.shumishumi.common.model.viewobject.ItemVO;
import id.thesis.shumishumi.common.model.viewobject.UserVO;
import id.thesis.shumishumi.rest.summary.ItemSummary;
import id.thesis.shumishumi.rest.summary.UserSummary;

import java.util.stream.Collectors;

public class SummaryConverter {
    public static ItemSummary toSummary(ItemVO vo) {
        if (vo == null) {
            return null;
        }
        ItemSummary summary = new ItemSummary();
        summary.setItemId(vo.getItemId());
        summary.setItemName(vo.getItemName());
        summary.setItemPrice(vo.getItemPrice());
        summary.setItemDescription(vo.getItemDescription());
        summary.setItemQuantity(vo.getItemQuantity());
        summary.setMerchantInfo(toSummary(vo.getMerchantInfo()));
        summary.setMerchantLevel(vo.getMerchantLevel().getInterestLevelName());
        summary.setHobby(vo.getHobby().getHobbyName());
        summary.setItemCategory(vo.getItemCategory().getCategoryName());
        summary.setItemImages(vo.getItemImages().stream().
                map(ItemImageVO::getItemImage).collect(Collectors.toList()));
        summary.setGmtCreate(vo.getGmtCreate());
        summary.setGmtModified(vo.getGmtModified());

        return summary;
    }

    public static UserSummary toSummary(UserVO vo) {
        if (vo == null) {
            return null;
        }
        UserSummary userSummary = new UserSummary();
        userSummary.setEmail(vo.getEmail());
        userSummary.setUsername(vo.getUsername());
        userSummary.setPhoneNumber(vo.getPhoneNumber());
        userSummary.setRole(vo.getRoleVO().getRoleName());
        userSummary.setGmtCreate(vo.getGmtCreate());
        userSummary.setGmtModified(vo.getGmtModified());

        return userSummary;
    }
}
