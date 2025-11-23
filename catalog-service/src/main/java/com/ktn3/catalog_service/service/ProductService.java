package com.ktn3.catalog_service.service;

import com.ktn3.catalog_service.dto.request.ProductCreateRequest;
import com.ktn3.catalog_service.dto.response.ProductDetailResponse;

import java.util.List;

public interface ProductService {
    ProductDetailResponse createProduct(ProductCreateRequest request);
    ProductDetailResponse getProductDetail(String productId);
    List<ProductDetailResponse> searchProducts(String keyword, String categoryId, String brandId, Boolean isActive);
}