package com.ktn3.catalog_service.dto.request;

import com.ktn3.catalog_service.entity.mongo.Variant;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Map;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductCreateRequest {
    String sku;
    String name;
    String slug;
    Double basePrice;
    String categoryId;
    String brandId;
    String description;
    String mainImage;        // ảnh chính
    List<String> images;     // ảnh phụ hoặc tất cả ảnh
    Map<String, Object> specifications;
    List<Variant> variants;
}
