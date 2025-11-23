package com.ktn3.catalog_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandUpdateRequest {
    String name;
    String slug;
    String description;
    Boolean active;

    String parentId;
}
