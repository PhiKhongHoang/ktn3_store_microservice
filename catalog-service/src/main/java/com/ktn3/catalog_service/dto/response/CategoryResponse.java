package com.ktn3.catalog_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryResponse {
    String id;
    String name;
    String slug;
    String description;
    Boolean active;

    String parentId;
}
