package com.ktn3.catalog_service.service;

import com.ktn3.catalog_service.dto.request.CategoryCreateRequest;
import com.ktn3.catalog_service.dto.request.CategoryUpdateRequest;
import com.ktn3.catalog_service.dto.response.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    CategoryResponse create(CategoryCreateRequest category);
    CategoryResponse update(String id, CategoryUpdateRequest request);
    void delete(String id);
    CategoryResponse getById(String id);
    Page<CategoryResponse> search(String keyword, Boolean isActive, Pageable pageable);
}
