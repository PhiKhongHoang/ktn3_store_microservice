package com.ktn3.catalog_service.repository.mysql.impl;

import com.ktn3.catalog_service.dto.response.CategoryResponse;
import com.ktn3.catalog_service.entity.mysql.Category;
import com.ktn3.catalog_service.repository.mysql.CategoryCriteriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryCriteriaRepositoryImpl implements CategoryCriteriaRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Page<Category> search(String keyword, Boolean isActive, Pageable pageable) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Category> cq = cb.createQuery(Category.class);
        Root<Category> root = cq.from(Category.class);

        List<Predicate> predicates = new ArrayList<>();

        if (keyword != null && !keyword.isBlank()) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"));
        }

        if (isActive != null) {
            predicates.add(cb.equal(root.get("active"), isActive));
        }

        cq.select(root)
                .where(predicates.toArray(new Predicate[0]))
                .orderBy(cb.asc(root.get("name")));

        TypedQuery<Category> query = em.createQuery(cq);

        // Phân trang
        query.setFirstResult((int) pageable.getOffset());
        query.setMaxResults(pageable.getPageSize());

        // Đếm tổng
        CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
        Root<Category> countRoot = countQuery.from(Category.class);
        countQuery.select(cb.count(countRoot))
                .where(predicates.toArray(new Predicate[0]));
        Long total = em.createQuery(countQuery).getSingleResult();

        return new PageImpl<>(query.getResultList(), pageable, total);
    }

}
