package id.thesis.shumishumi.common.util.comparator;

import id.thesis.shumishumi.common.model.viewobject.ItemVO;

import java.util.Comparator;

public class ItemVOGmtCreateComparator implements Comparator<ItemVO> {
    @Override
    public int compare(ItemVO t1, ItemVO t2) {
        return t1.getGmtCreate().before(t2.getGmtCreate()) ? 1 : 0;
    }
}
