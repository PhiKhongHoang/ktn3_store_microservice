package com.ktn3.catalog_service.entity.mongo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Variant {
    String sku;
    String name;
    Double price;
    Integer stock;
    Map<String, Object> attributes;
}

