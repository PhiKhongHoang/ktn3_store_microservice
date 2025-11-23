package com.ktn3.catalog_service.repository.mysql.impl;

import com.ktn3.catalog_service.entity.mysql.Product;
import com.ktn3.catalog_service.repository.mysql.ProductCriteriaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductCriteriaRepositoryImpl implements ProductCriteriaRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Product> search(String keyword, String categoryId, String brandId, Boolean isActive) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Product> query = cb.createQuery(Product.class);
        Root<Product> root = query.from(Product.class);

        List<Predicate> predicates = new ArrayList<>();

        if (keyword != null && !keyword.isBlank()) {
            predicates.add(cb.like(cb.lower(root.get("name")), "%" + keyword.toLowerCase() + "%"));
        }
        if (categoryId != null) {
            predicates.add(cb.equal(root.get("category").get("id"), categoryId));
        }
        if (brandId != null) {
            predicates.add(cb.equal(root.get("brand").get("id"), brandId));
        }
        if (isActive != null) {
            predicates.add(cb.equal(root.get("isActive"), isActive));
        }

        query.where(predicates.toArray(new Predicate[0]));
        query.orderBy(cb.asc(root.get("id")));

        return em.createQuery(query).getResultList();
    }
}
