package com.ktn3.auth_service.mapper;

import com.ktn3.auth_service.dto.request.ProfileCreationRequest;
import com.ktn3.auth_service.dto.request.UserCreationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    ProfileCreationRequest toProfileCreationRequest(UserCreationRequest request);
}
