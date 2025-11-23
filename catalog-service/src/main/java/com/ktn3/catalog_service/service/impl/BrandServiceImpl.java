package com.ktn3.catalog_service.service.impl;

import com.ktn3.catalog_service.dto.request.BrandCreateRequest;
import com.ktn3.catalog_service.dto.request.BrandUpdateRequest;
import com.ktn3.catalog_service.dto.response.BrandResponse;
import com.ktn3.catalog_service.entity.mysql.Brand;
import com.ktn3.catalog_service.exception.AppException;
import com.ktn3.catalog_service.exception.ErrorCode;
import com.ktn3.catalog_service.mapper.BrandMapper;
import com.ktn3.catalog_service.repository.mysql.BrandRepository;
import com.ktn3.catalog_service.service.BrandService;
import com.ktn3.catalog_service.utils.SlugUtils;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
@Transactional
public class BrandServiceImpl implements BrandService {

    BrandMapper brandMapper;
    BrandRepository brandRepository;

    @Override
    public BrandResponse create(BrandCreateRequest request) {
        if (brandRepository.existsByNameIgnoreCase(request.getName())) {
            throw new AppException(ErrorCode.NAME_EXISTS);
        }

        Brand brand = brandMapper.toEntity(request);

        if (brand.getSlug() == null || brand.getSlug().isBlank()) {
            // User không nhập → tự tạo từ name
            brand.setSlug(generateUniqueSlug(brand.getName()));
        } else {
            // User nhập slug → check trùng, nếu trùng báo lỗi
            if (brandRepository.existsBySlugIgnoreCase(brand.getSlug())) {
                throw new AppException(ErrorCode.SLUG_EXISTS);
            }
            // Nếu không trùng thì dùng slug do user nhập
        }

        Brand saved = brandRepository.save(brand);
        return brandMapper.toResponse(saved);
    }

    @Override
    public BrandResponse update(String id, BrandUpdateRequest request) {
        Brand existing = findById(id);

        // Check tên trùng nhưng bỏ qua chính brand hiện tại
        if (request.getName() != null && !request.getName().equalsIgnoreCase(existing.getName())) {
            if (brandRepository.existsByNameIgnoreCaseAndIdNot(request.getName(), existing.getId())) {
                throw new AppException(ErrorCode.NAME_EXISTS);
            }
            // Nếu name thay đổi → generate slug mới
            existing.setSlug(generateUniqueSlug(request.getName()));
        }

        // Nếu user nhập slug thủ công → check trùng
        if (request.getSlug() != null && !request.getSlug().isBlank()) {
            if (brandRepository.existsBySlugIgnoreCaseAndIdNot(request.getSlug(), existing.getId())) {
                throw new AppException(ErrorCode.SLUG_EXISTS);
            }
            existing.setSlug(request.getSlug());
        }

        // Cập nhật các field còn lại
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setActive(request.getActive());

        Brand updated = brandRepository.save(existing);
        return brandMapper.toResponse(updated);
    }


    @Override
    public void delete(String id) {
        if (!brandRepository.existsById(id)) {
            throw new AppException(ErrorCode.BRAND_NOT_EXISTED);
        }
        brandRepository.deleteById(id);
    }

    @Override
    public BrandResponse getById(String id) {
        Brand brand = findById(id);
        return brandMapper.toResponse(brand);
    }

    @Override
    public Page<BrandResponse> search(String keyword, Boolean isActive, Pageable pageable) {
        Page<Brand> brands = brandRepository.search(keyword, isActive, pageable);
        List<BrandResponse> responses = brands.getContent()
                .stream()
                .map(brandMapper::toResponse)
                .toList();
        return new PageImpl<>(responses, pageable, brands.getTotalElements());
    }

    // -------- Private Helpers --------

    private String generateUniqueSlug(String name) {
        String baseSlug = SlugUtils.toSlug(name);
        String slug = baseSlug;
        int counter = 1;

        while (brandRepository.existsBySlugIgnoreCase(slug)) {
            slug = baseSlug + "-" + counter++;
        }

        return slug;
    }


    private Brand findById(String id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.BRAND_NOT_EXISTED));
    }

}
