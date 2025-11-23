package com.ktn3.catalog_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryUpdateRequest {
    String name;
    String slug;
    String description;
    Boolean active;

    String parentId;
}
