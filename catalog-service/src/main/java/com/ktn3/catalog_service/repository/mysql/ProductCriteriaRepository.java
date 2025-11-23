package com.ktn3.catalog_service.repository.mysql;

import com.ktn3.catalog_service.entity.mysql.Product;

import java.util.List;

public interface ProductCriteriaRepository {
    List<Product> search(String keyword, String categoryId, String brandId, Boolean isActive);
}