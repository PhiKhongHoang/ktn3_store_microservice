package com.ktn3.catalog_service.repository.mysql;

import com.ktn3.catalog_service.dto.response.CategoryResponse;
import com.ktn3.catalog_service.entity.mysql.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryCriteriaRepository {
    Page<Category> search(String keyword, Boolean isActive, Pageable pageable);
}
