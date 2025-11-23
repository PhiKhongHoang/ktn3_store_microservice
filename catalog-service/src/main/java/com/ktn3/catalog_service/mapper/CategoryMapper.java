package com.ktn3.catalog_service.mapper;

import com.ktn3.catalog_service.dto.request.CategoryCreateRequest;
import com.ktn3.catalog_service.dto.response.CategoryResponse;
import com.ktn3.catalog_service.entity.mysql.Category;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toEntity(CategoryCreateRequest request);
    CategoryResponse toResponse(Category category);

}
