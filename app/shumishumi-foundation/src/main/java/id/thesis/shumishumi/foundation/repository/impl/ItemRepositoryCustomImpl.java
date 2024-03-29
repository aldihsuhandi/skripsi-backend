package id.thesis.shumishumi.foundation.repository.impl;

import id.thesis.shumishumi.facade.model.context.SortingContext;
import id.thesis.shumishumi.foundation.model.result.ItemDO;
import id.thesis.shumishumi.foundation.repository.ItemRepositoryCustom;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<ItemDO> queryFilter(ItemDO itemDO, Long minPrice, Long maxPrice,
                                    SortingContext sortingContext, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ItemDO> cq = cb.createQuery(ItemDO.class);
        Root<ItemDO> item = cq.from(ItemDO.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(item.get("isDeleted"), false));
        predicates.add(cb.equal(item.get("isApproved"), true));

        if (StringUtils.isNotEmpty(itemDO.getItemName())) {
            predicates.add(cb.like(cb.lower(item.get("itemName")),
                    "%" + itemDO.getItemName().toLowerCase() + "%"));
        }

        if (StringUtils.isNotEmpty(itemDO.getMerchantLevelId())) {
            predicates.add(cb.equal(item.get("merchantLevelId"),
                    itemDO.getMerchantLevelId()));
        }

//        if(StringUtils.isNotEmpty(itemDO.getUserInterestLevel())) {
//            predicates.add(cb.equal(item.get("userLevelId"),
//                    itemDO.getUserInterestLevel()));
//        }

        if (StringUtils.isNotEmpty(itemDO.getMerchantId())) {
            predicates.add(cb.equal(item.get("merchantId"),
                    itemDO.getMerchantId()));
        }

        if (StringUtils.isNotEmpty(itemDO.getHobbyId())) {
            predicates.add(cb.equal(item.get("hobbyId"),
                    itemDO.getHobbyId()));
        }

        if (StringUtils.isNotEmpty(itemDO.getCategoryId())) {
            predicates.add(cb.equal(item.get("categoryId"),
                    itemDO.getCategoryId()));
        }

        if (minPrice != null && maxPrice != null) {
            predicates.add(cb.between(item.get("itemPrice"), minPrice, maxPrice));
        } else if (minPrice != null) {
            predicates.add(cb.ge(item.get("itemPrice"), minPrice));
        } else if (maxPrice != null) {
            predicates.add(cb.le(item.get("itemPrice"), maxPrice));
        }

        String sorting = sortingContext.getSorting();
        String sortingType = sortingContext.getSortingType();
        if (StringUtils.equals(sorting, "Price")) {
            if (StringUtils.equals(sortingType, "Descending")) {
                cq.orderBy(cb.desc(item.get("itemPrice")));
            } else {
                cq.orderBy(cb.asc(item.get("itemPrice")));
            }
        } else if (StringUtils.equals(sorting, "Alphabetical")) {
            if (StringUtils.equals(sortingType, "Descending")) {
                cq.orderBy(cb.desc(item.get("itemName")));
            } else {
                cq.orderBy(cb.asc(item.get("itemName")));
            }
        } else if (StringUtils.equals(sorting, "Created")) {
            if (StringUtils.equals(sortingType, "Descending")) {
                cq.orderBy(cb.desc(item.get("gmtCreate")));
            } else {
                cq.orderBy(cb.asc(item.get("gmtCreate")));
            }
        } else if (StringUtils.equals(sorting, "Interest level")) {
            if (StringUtils.equals(sortingType, "Descending")) {
                cq.orderBy(cb.desc(item.get("merchantLevelId"))).
                        orderBy(cb.desc(item.get("userLevelId")));
            } else {
                cq.orderBy(cb.asc(item.get("merchantLevelId"))).
                        orderBy(cb.asc(item.get("userLevelId")));
            }
        } else if (StringUtils.equals(sorting, "Review")) {
            if (StringUtils.equals(sortingType, "Descending")) {
                cq.orderBy(cb.desc(item.get("review")));
            } else {
                cq.orderBy(cb.asc(item.get("review")));
            }
        }

        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        TypedQuery<ItemDO> typedQuery = entityManager.createQuery(cq);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());

        List<ItemDO> queryResult = typedQuery.getResultList();
        long totalRows = countWithFilter(itemDO, minPrice, maxPrice);

        return new PageImpl<>(queryResult, pageable, totalRows);
    }

    @Override
    public long countWithFilter(ItemDO itemDO, Long minPrice, Long maxPrice) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ItemDO> item = cq.from(ItemDO.class);
        List<Predicate> predicates = new ArrayList<>();

        predicates.add(cb.equal(item.get("isDeleted"), false));
        predicates.add(cb.equal(item.get("isApproved"), true));

        if (StringUtils.isNotEmpty(itemDO.getItemName())) {
            predicates.add(cb.like(cb.lower(item.get("itemName")),
                    "%" + itemDO.getItemName().toLowerCase() + "%"));
        }

        if (StringUtils.isNotEmpty(itemDO.getMerchantLevelId())) {
            predicates.add(cb.equal(item.get("merchantLevelId"),
                    itemDO.getMerchantLevelId()));
        }

//        if(StringUtils.isNotEmpty(itemDO.getUserInterestLevel())) {
//            predicates.add(cb.equal(item.get("userInterestLevel"),
//                    itemDO.getUserInterestLevel()));
//        }

        if (StringUtils.isNotEmpty(itemDO.getMerchantId())) {
            predicates.add(cb.equal(item.get("merchantId"),
                    itemDO.getMerchantId()));
        }

        if (StringUtils.isNotEmpty(itemDO.getHobbyId())) {
            predicates.add(cb.equal(item.get("hobbyId"),
                    itemDO.getHobbyId()));
        }

        if (StringUtils.isNotEmpty(itemDO.getCategoryId())) {
            predicates.add(cb.equal(item.get("categoryId"),
                    itemDO.getCategoryId()));
        }

        if (minPrice != null && maxPrice != null) {
            predicates.add(cb.between(item.get("itemPrice"), minPrice, maxPrice));
        } else if (minPrice != null) {
            predicates.add(cb.ge(item.get("itemPrice"), minPrice));
        } else if (maxPrice != null) {
            predicates.add(cb.le(item.get("itemPrice"), maxPrice));
        }

        cq.select(cb.count(item)).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(cq).getSingleResult();
    }
}
