package com.ktn3.profile_service.mapper;

import com.ktn3.profile_service.dto.request.ProfileCreationRequest;
import com.ktn3.profile_service.dto.response.ProfileResponse;
import com.ktn3.profile_service.entity.Profile;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    Profile toProfile(ProfileCreationRequest request);

    ProfileResponse toProfileResponse(Profile entity);

    @AfterMapping
    default void setDefaultAvatar(Profile entity, @MappingTarget ProfileResponse response) {
        if (response.getAvatar() == null || response.getAvatar().isBlank()) {
            response.setAvatar("https://firebasestorage.googleapis.com/v0/b/your-app.appspot.com/o/default_avatar.png?alt=media");
        }
    }

}
