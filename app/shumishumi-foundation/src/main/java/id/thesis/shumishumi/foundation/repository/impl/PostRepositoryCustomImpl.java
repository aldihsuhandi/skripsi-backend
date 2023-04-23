package id.thesis.shumishumi.foundation.repository.impl;

import id.thesis.shumishumi.foundation.model.result.PostDO;
import id.thesis.shumishumi.foundation.repository.PostRepositoryCustom;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

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
public class PostRepositoryCustomImpl implements PostRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Page<PostDO> queryFilter(String title, String userId, List<String> tags, Pageable pageable) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<PostDO> cq = cb.createQuery(PostDO.class);

        Root<PostDO> post = cq.from(PostDO.class);
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotEmpty(title)) {
            predicates.add(cb.like(cb.lower(post.get("title")), "%" + title.toLowerCase() + "%"));
        }

        if (StringUtils.isNotEmpty(userId)) {
            predicates.add(cb.equal(post.get("userId"), userId));
        }

        if (!CollectionUtils.isEmpty(tags)) {
            tags.forEach(tag -> {
                predicates.add(cb.like(cb.lower(post.get("tags")), "%" + tag.toLowerCase() + "%"));
            });
        }

        cq.where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        TypedQuery<PostDO> typedQuery = entityManager.createQuery(cq);
        typedQuery.setFirstResult((int) pageable.getOffset());
        typedQuery.setMaxResults(pageable.getPageSize());


        List<PostDO> queryResult = typedQuery.getResultList();
        long totalRows = countWithFilter(title, userId, tags);

        return new PageImpl<>(queryResult, pageable, totalRows);
    }

    @Override
    public long countWithFilter(String title, String userId, List<String> tags) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<PostDO> post = cq.from(PostDO.class);
        List<Predicate> predicates = new ArrayList<>();

        if (StringUtils.isNotEmpty(title)) {
            predicates.add(cb.like(cb.lower(post.get("title")), "%" + title.toLowerCase() + "%"));
        }

        if (StringUtils.isNotEmpty(userId)) {
            predicates.add(cb.equal(post.get("userId"), userId));
        }

        if (!CollectionUtils.isEmpty(tags)) {
            tags.forEach(tag -> {
                predicates.add(cb.like(cb.lower(post.get("tags")), "%" + tag.toLowerCase() + "%"));
            });
        }

        cq.select(cb.count(post)).where(cb.and(predicates.toArray(new Predicate[predicates.size()])));
        return entityManager.createQuery(cq).getSingleResult();
    }
}
