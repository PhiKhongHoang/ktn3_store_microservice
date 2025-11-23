package com.ktn3.catalog_service.dto.response;

import com.ktn3.catalog_service.entity.mongo.ProductDetail;
import com.ktn3.catalog_service.entity.mongo.Variant;
import com.ktn3.catalog_service.entity.mysql.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDetailResponse {
    private String id;
    private String name;
    private String sku;
    private String slug;
    private Double basePrice;
    private String categoryName;
    private String brandName;
    private String description;
    private String mainImage;
    private List<String> images;
    private Map<String, Object> specifications;
    private List<Variant> variants;

    public static ProductDetailResponse from(Product p, ProductDetail d) {
        return ProductDetailResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .sku(p.getSku())
                .slug(p.getSlug())
                .basePrice(p.getBasePrice())
                .categoryName(p.getCategory().getName())
                .brandName(p.getBrand().getName())
                .description(d != null ? d.getDescription() : null)
                .mainImage(d != null ? d.getMainImage() : null)
                .images(d != null ? d.getImages() : null)
                .specifications(d != null ? d.getSpecifications() : null)
                .variants(d != null ? d.getVariants() : null)
                .build();
    }
}
