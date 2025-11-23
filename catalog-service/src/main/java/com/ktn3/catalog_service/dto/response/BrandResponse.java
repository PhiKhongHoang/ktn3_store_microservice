package com.ktn3.catalog_service.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandResponse {
    String id;
    String name;
    String slug;
    String description;
    Boolean active;

    String parentId;
}
