package com.ktn3.catalog_service.controller;

import com.ktn3.catalog_service.dto.ApiResponse;
import com.ktn3.catalog_service.dto.PageResponse;
import com.ktn3.catalog_service.dto.request.BrandCreateRequest;
import com.ktn3.catalog_service.dto.request.BrandUpdateRequest;
import com.ktn3.catalog_service.dto.response.BrandResponse;
import com.ktn3.catalog_service.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/brands")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class BrandController {

    BrandService brandService;

    // -------- CREATE --------
    @PostMapping
    public ResponseEntity<ApiResponse<BrandResponse>> create(@Valid @RequestBody BrandCreateRequest request) {
        BrandResponse response = brandService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<BrandResponse>builder()
                        .message("Brand created successfully")
                        .result(response)
                        .build());
    }

    // -------- UPDATE --------
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BrandResponse>> update(
            @PathVariable String id,
            @RequestBody BrandUpdateRequest request) {

        BrandResponse response = brandService.update(id, request);
        return ResponseEntity.ok(ApiResponse.<BrandResponse>builder()
                .message("Brand updated successfully")
                .result(response)
                .build());
    }

    // -------- DELETE --------
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable String id) {
        brandService.delete(id);
        return ResponseEntity.ok(ApiResponse.<Void>builder()
                .message("Brand deleted successfully")
                .build());
    }

    // -------- GET BY ID --------
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BrandResponse>> getById(@PathVariable String id) {
        BrandResponse response = brandService.getById(id);
        return ResponseEntity.ok(ApiResponse.<BrandResponse>builder()
                .message("Get Brand successfully")
                .result(response)
                .build());
    }

    // -------- SEARCH / PAGINATION --------
    @GetMapping("/search")
    public ResponseEntity<ApiResponse<PageResponse<BrandResponse>>> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BrandResponse> result = brandService.search(keyword, isActive, pageable);

        PageResponse<BrandResponse> pageResponse = PageResponse.<BrandResponse>builder()
                .data(result.getContent())
                .currentPage(result.getNumber())
                .pageSize(result.getSize())
                .totalElements(result.getTotalElements())
                .totalPages(result.getTotalPages())
                .build();

        return ResponseEntity.ok(ApiResponse.<PageResponse<BrandResponse>>builder()
                .message("Search brands successfully")
                .result(pageResponse)
                .build());
    }

}
