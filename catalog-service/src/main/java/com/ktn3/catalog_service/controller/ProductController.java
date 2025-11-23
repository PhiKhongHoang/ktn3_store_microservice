package com.ktn3.catalog_service.controller;

import com.ktn3.catalog_service.dto.request.ProductCreateRequest;
import com.ktn3.catalog_service.dto.response.ProductDetailResponse;
import com.ktn3.catalog_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductDetailResponse> create(@RequestBody ProductCreateRequest req) {
        return ResponseEntity.ok(productService.createProduct(req));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailResponse> get(@PathVariable String id) {
        return ResponseEntity.ok(productService.getProductDetail(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDetailResponse>> search(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String categoryId,
            @RequestParam(required = false) String brandId,
            @RequestParam(required = false) Boolean isActive
    ) {
        return ResponseEntity.ok(productService.searchProducts(keyword, categoryId, brandId, isActive));
    }
}