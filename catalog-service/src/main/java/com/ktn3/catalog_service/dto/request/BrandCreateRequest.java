package com.ktn3.catalog_service.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandCreateRequest {
    String name;
    String slug;
    String description;
    Boolean active = true;

    String parentId;
}
