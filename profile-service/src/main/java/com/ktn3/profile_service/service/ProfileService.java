package com.ktn3.profile_service.service;

import com.ktn3.profile_service.dto.request.AvatarUpdateRequest;
import com.ktn3.profile_service.dto.request.ProfileCreationRequest;
import com.ktn3.profile_service.dto.response.ProfileResponse;
import com.ktn3.profile_service.entity.Profile;
import com.ktn3.profile_service.exception.AppException;
import com.ktn3.profile_service.exception.ErrorCode;
import com.ktn3.profile_service.mapper.ProfileMapper;
import com.ktn3.profile_service.repository.ProfileRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ProfileService {
    ProfileRepository profileRepository;

    ProfileMapper profileMapper;

    public ProfileResponse createProfile(ProfileCreationRequest request) {
        Profile profile = profileMapper.toProfile(request);
        profile = profileRepository.save(profile);

        return profileMapper.toProfileResponse(profile);
    }

    public ProfileResponse getByUserId(String userId) {
        Profile profile =
                profileRepository.findByUserId(userId)
                        .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return profileMapper.toProfileResponse(profile);
    }

    public ProfileResponse getProfile(String id) {
        Profile profile =
                profileRepository.findById(id).orElseThrow(
                        () -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return profileMapper.toProfileResponse(profile);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public List<ProfileResponse> getAllProfiles() {
        var profiles = profileRepository.findAll();

        return profiles.stream().map(profileMapper::toProfileResponse).toList();
    }

    public ProfileResponse getMyProfile() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        var profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        return profileMapper.toProfileResponse(profile);
    }

    public ProfileResponse updateAvatar(AvatarUpdateRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        Profile profile = profileRepository.findByUserId(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        profile.setAvatar(request.getAvatar());
        profile = profileRepository.save(profile);

        return profileMapper.toProfileResponse(profile);
    }

}
