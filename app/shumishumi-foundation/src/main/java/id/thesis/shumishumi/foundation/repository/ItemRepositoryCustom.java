package id.thesis.shumishumi.foundation.repository;

import id.thesis.shumishumi.foundation.model.result.ItemDO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ItemRepositoryCustom {
    Page<ItemDO> queryFilter(ItemDO itemDO, Long minPrice, Long maxPrice, Pageable pageable);

    long countWithFilter(ItemDO itemDO, Long minPrice, Long maxPrice);
}
