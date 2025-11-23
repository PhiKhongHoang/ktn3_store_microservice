package com.ktn3.catalog_service.mapper;

import com.ktn3.catalog_service.dto.request.BrandCreateRequest;
import com.ktn3.catalog_service.dto.response.BrandResponse;
import com.ktn3.catalog_service.entity.mysql.Brand;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand toEntity(BrandCreateRequest request);
    BrandResponse toResponse(Brand brand);

}
