package com.ktn3.catalog_service.repository.mysql;

import com.ktn3.catalog_service.entity.mysql.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandCriteriaRepository {
    Page<Brand> search(String keyword, Boolean isActive, Pageable pageable);
}
