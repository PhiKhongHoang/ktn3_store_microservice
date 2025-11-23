package com.ktn3.catalog_service.controller;

import com.ktn3.catalog_service.dto.ApiResponse;
import com.ktn3.catalog_service.dto.PageResponse;
import com.ktn3.catalog_service.dto.request.CategoryCreateRequest;
import com.ktn3.catalog_service.dto.request.CategoryUpdateRequest;
import com.ktn3.catalog_service.dto.response.CategoryResponse;
import com.ktn3.catalog_service.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class CategoryController {

    CategoryService categoryService;

    // -------- CREATE --------
    @PostMapping
    public ResponseEntity<ApiResponse<CategoryResponse>> create(@Valid @RequestBody CategoryCreateRequest request) {
        CategoryResponse response = categoryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<CategoryResponse>builder()
                        .message("Category created successfully")
                        .result(response)
                        .build());
    }

    // -------- UPDATE --------
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> update(
            @PathVariable String id,
            @RequestBody CategoryUpdateRequest request) {

        CategoryResponse response = categoryService.update(id, request);
        return ResponseEntity.ok(ApiResponse.<CategoryResponse>builder()
                .message("Category updated successfully")
                .result(response)
                .build());
    }

    // -------- DELETE --------
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        categoryService.delete(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .message("Category deleted successfully")
                .build());
    }

    // -------- GET BY ID --------
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<CategoryResponse>> getById(@PathVariable String id) {
        CategoryResponse response = categoryService.getById(id);
        return ResponseEntity.ok(ApiResponse.<CategoryResponse>builder()
                .message("Get category successfully")
                .result(response)
                .build());
    }

    // -------- SEARCH / PAGINATION --------
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<CategoryResponse>>> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<CategoryResponse> result = categoryService.search(keyword, isActive, pageable);

        PageResponse<CategoryResponse> pageResponse = PageResponse.<CategoryResponse>builder()
                .data(result.getContent())
                .currentPage(result.getNumber())
                .pageSize(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .build();

        return ResponseEntity.ok(ApiResponse.<PageResponse<CategoryResponse>>builder()
                .message("Search categories successfully")
                .result(pageResponse)
                .build());
    }

}
