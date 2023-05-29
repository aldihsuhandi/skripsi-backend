package id.thesis.shumishumi.common.util.comparator;

import id.thesis.shumishumi.facade.model.enumeration.InterestLevelEnum;
import id.thesis.shumishumi.facade.model.viewobject.ItemVO;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.Comparator;

@Getter
public class ItemVOComparator implements Comparator<ItemVO> {

    private final String sorting;
    private final String sortingType;

    public ItemVOComparator(String sorting, String sortingType) {
        this.sorting = sorting;
        this.sortingType = sortingType;
    }

    @Override
    public int compare(ItemVO itemVO, ItemVO t1) {
        int res = 0;
        if (StringUtils.equals(sorting, "Price")) {
            if (StringUtils.equals(sortingType, "Descending")) {
                res = Long.compare(itemVO.getItemPrice(), t1.getItemPrice());
            } else {
                res = Long.compare(t1.getItemPrice(), itemVO.getItemPrice());
            }
        } else if (StringUtils.equals(sorting, "Alphabetical")) {
            if (StringUtils.equals(sortingType, "Descending")) {
                res = StringUtils.compare(t1.getItemName(), itemVO.getItemName());
            } else {
                res = StringUtils.compare(itemVO.getItemName(), t1.getItemName());
            }
        } else if (StringUtils.equals(sorting, "Created")) {
            if (StringUtils.equals(sortingType, "Descending")) {
                res = itemVO.getGmtCreate().before(t1.getGmtCreate()) ? 1 : 0;
            } else {
                res = itemVO.getGmtCreate().before(t1.getGmtCreate()) ? 0 : 1;
            }
        } else if (StringUtils.equals(sorting, "Interest Level")) {
            if (StringUtils.equals(sortingType, "Descending")) {
                res = compareInterestLevel(itemVO, t1);
            } else {
                res = compareInterestLevel(t1, itemVO);
            }
        }

        return res;
    }

    private int compareInterestLevel(ItemVO i1, ItemVO i2) {
        int il1 = calculateInterestLevel(i1);
        int il2 = calculateInterestLevel(i2);

        return il1 - il2;
    }

    private int calculateInterestLevel(ItemVO item) {
        InterestLevelEnum merchant = InterestLevelEnum.findByName(item.getMerchantLevel().getInterestLevelName());
        InterestLevelEnum user = InterestLevelEnum.findByName(item.getUserLevel().getInterestLevelName());

        int merchantIndex = merchant == null ? 0 : merchant.getIndex();
        int userIndex = user == null ? 0 : user.getIndex();

        return merchantIndex + userIndex;
    }
}
