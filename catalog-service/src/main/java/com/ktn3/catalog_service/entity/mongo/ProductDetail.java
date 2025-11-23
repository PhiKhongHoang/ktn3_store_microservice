package com.ktn3.catalog_service.entity.mongo;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.util.List;
import java.util.Map;

@Document(collection = "product_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ProductDetail {
    @MongoId
    String id;
    String productId;
    String description;
    String mainImage;        // ảnh chính
    List<String> images;     // ảnh phụ hoặc tất cả ảnh
    Map<String, Object> specifications;
    List<Variant> variants;
}

