package com.ktn3.catalog_service.service.impl;

import com.ktn3.catalog_service.dto.request.ProductCreateRequest;
import com.ktn3.catalog_service.dto.response.ProductDetailResponse;
import com.ktn3.catalog_service.entity.mongo.ProductDetail;
import com.ktn3.catalog_service.entity.mysql.Brand;
import com.ktn3.catalog_service.entity.mysql.Category;
import com.ktn3.catalog_service.entity.mysql.Product;
import com.ktn3.catalog_service.repository.mongo.ProductDetailRepository;
import com.ktn3.catalog_service.repository.mysql.BrandRepository;
import com.ktn3.catalog_service.repository.mysql.CategoryRepository;
import com.ktn3.catalog_service.repository.mysql.ProductRepository;
import com.ktn3.catalog_service.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final BrandRepository brandRepo;
    private final ProductDetailRepository productDetailRepo;

    @Transactional
    @Override
    public ProductDetailResponse createProduct(ProductCreateRequest request) {
        Category category = categoryRepo.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));
        Brand brand = brandRepo.findById(request.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));

        Product product = Product.builder()
                .sku(request.getSku())
                .name(request.getName())
                .slug(request.getSlug())
                .basePrice(request.getBasePrice())
                .category(category)
                .brand(brand)
                .isActive(true)
                .build();
        productRepo.save(product);

        ProductDetail detail = ProductDetail.builder()
                .productId(product.getId())
                .description(request.getDescription())
                .mainImage(request.getMainImage())
                .images(request.getImages())
                .specifications(request.getSpecifications())
                .variants(request.getVariants())
                .build();
        productDetailRepo.save(detail);

        return ProductDetailResponse.from(product, detail);
    }

    @Override
    public ProductDetailResponse getProductDetail(String productId) {
        Product product = productRepo.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        ProductDetail detail = productDetailRepo.findByProductId(productId)
                .orElse(null);
        return ProductDetailResponse.from(product, detail);
    }

    @Override
    public List<ProductDetailResponse> searchProducts(String keyword, String categoryId, String brandId, Boolean isActive) {
        return productRepo.search(keyword, categoryId, brandId, isActive)
                .stream()
                .map(p -> {
                    var detail = productDetailRepo.findByProductId(p.getId()).orElse(null);
                    return ProductDetailResponse.from(p, detail);
                })
                .toList();
    }
}
