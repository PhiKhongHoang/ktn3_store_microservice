package com.ktn3.catalog_service.service;

import com.ktn3.catalog_service.dto.request.BrandCreateRequest;
import com.ktn3.catalog_service.dto.request.BrandUpdateRequest;
import com.ktn3.catalog_service.dto.response.BrandResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BrandService {
    BrandResponse create(BrandCreateRequest brand);
    BrandResponse update(String id, BrandUpdateRequest request);
    void delete(String id);
    BrandResponse getById(String id);
    Page<BrandResponse> search(String keyword, Boolean isActive, Pageable pageable);
}
