package com.ktn3.catalog_service.entity.mysql;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "products")
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String id;

    String sku;
    String name;
    String slug;
    Double basePrice;
    Boolean isActive = true;

    @ManyToOne
    @JoinColumn(name = "brand_id")
    Brand brand;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;
}
