package com.ktn3.auth_service.mapper;

import com.ktn3.auth_service.dto.request.RoleRequest;
import com.ktn3.auth_service.dto.response.RoleResponse;
import com.ktn3.auth_service.entity.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {
    Role toRole(RoleRequest request);

    RoleResponse toRoleResponse(Role role);
}
