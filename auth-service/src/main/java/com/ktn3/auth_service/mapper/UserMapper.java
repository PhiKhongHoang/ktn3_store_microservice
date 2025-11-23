package com.ktn3.auth_service.mapper;

import com.ktn3.auth_service.dto.request.UserCreationRequest;
import com.ktn3.auth_service.dto.request.UserUpdateRequest;
import com.ktn3.auth_service.dto.response.UserResponse;
import com.ktn3.auth_service.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);
}
