package com.ktn3.catalog_service.service.impl;

import com.ktn3.catalog_service.dto.request.CategoryCreateRequest;
import com.ktn3.catalog_service.dto.request.CategoryUpdateRequest;
import com.ktn3.catalog_service.dto.response.CategoryResponse;
import com.ktn3.catalog_service.entity.mysql.Category;
import com.ktn3.catalog_service.exception.AppException;
import com.ktn3.catalog_service.exception.ErrorCode;
import com.ktn3.catalog_service.mapper.CategoryMapper;
import com.ktn3.catalog_service.repository.mysql.CategoryRepository;
import com.ktn3.catalog_service.service.CategoryService;
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
public class CategoryServiceImpl implements CategoryService {

    CategoryMapper categoryMapper;
    CategoryRepository categoryRepository;

    @Override
    public CategoryResponse create(CategoryCreateRequest request) {
        if (categoryRepository.existsByNameIgnoreCase(request.getName())) {
            throw new AppException(ErrorCode.NAME_EXISTS);
        }

        Category category = categoryMapper.toEntity(request);

        if (category.getSlug() == null || category.getSlug().isBlank()) {
            // User không nhập → tự tạo từ name
            category.setSlug(generateUniqueSlug(category.getName()));
        } else {
            // User nhập slug → check trùng, nếu trùng báo lỗi
            if (categoryRepository.existsBySlugIgnoreCase(category.getSlug())) {
                throw new AppException(ErrorCode.SLUG_EXISTS);
            }
            // Nếu không trùng thì dùng slug do user nhập
        }

        Category saved = categoryRepository.save(category);
        return categoryMapper.toResponse(saved);
    }

    @Override
    public CategoryResponse update(String id, CategoryUpdateRequest request) {
        Category existing = findById(id);

        // Check tên trùng nhưng bỏ qua chính category hiện tại
        if (request.getName() != null && !request.getName().equalsIgnoreCase(existing.getName())) {
            if (categoryRepository.existsByNameIgnoreCaseAndIdNot(request.getName(), existing.getId())) {
                throw new AppException(ErrorCode.NAME_EXISTS);
            }
            // Nếu name thay đổi → generate slug mới
            existing.setSlug(generateUniqueSlug(request.getName()));
        }

        // Nếu user nhập slug thủ công → check trùng
        if (request.getSlug() != null && !request.getSlug().isBlank()) {
            if (categoryRepository.existsBySlugIgnoreCaseAndIdNot(request.getSlug(), existing.getId())) {
                throw new AppException(ErrorCode.SLUG_EXISTS);
            }
            existing.setSlug(request.getSlug());
        }

        // Cập nhật các field còn lại
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setActive(request.getActive());

        Category updated = categoryRepository.save(existing);
        return categoryMapper.toResponse(updated);
    }


    @Override
    public void delete(String id) {
        if (!categoryRepository.existsById(id)) {
            throw new AppException(ErrorCode.CATEGORY_NOT_EXISTED);
        }
        categoryRepository.deleteById(id);
    }

    @Override
    public CategoryResponse getById(String id) {
        Category category = findById(id);
        return categoryMapper.toResponse(category);
    }

    @Override
    public Page<CategoryResponse> search(String keyword, Boolean isActive, Pageable pageable) {
        Page<Category> categories = categoryRepository.search(keyword, isActive, pageable);
        List<CategoryResponse> responses = categories.getContent()
                .stream()
                .map(categoryMapper::toResponse)
                .toList();
        return new PageImpl<>(responses, pageable, categories.getTotalElements());
    }

    // -------- Private Helpers --------

    private String generateUniqueSlug(String name) {
        String baseSlug = SlugUtils.toSlug(name);
        String slug = baseSlug;
        int counter = 1;

        while (categoryRepository.existsBySlugIgnoreCase(slug)) {
            slug = baseSlug + "-" + counter++;
        }

        return slug;
    }


    private Category findById(String id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.CATEGORY_NOT_EXISTED));
    }

}
