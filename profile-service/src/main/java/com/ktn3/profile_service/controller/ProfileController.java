package com.ktn3.profile_service.controller;

import com.ktn3.profile_service.dto.ApiResponse;
import com.ktn3.profile_service.dto.request.AvatarUpdateRequest;
import com.ktn3.profile_service.dto.response.ProfileResponse;
import com.ktn3.profile_service.service.ProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/users")
public class ProfileController {
    ProfileService profileService;

    @GetMapping("/{profileId}")
    ApiResponse<ProfileResponse> getProfile(@PathVariable String profileId) {
        return ApiResponse.<ProfileResponse>builder()
                .result(profileService.getProfile(profileId))
                .build();
    }

    @GetMapping
    ApiResponse<List<ProfileResponse>> getAllProfiles() {
        return ApiResponse.<List<ProfileResponse>>builder()
                .result(profileService.getAllProfiles())
                .build();
    }

    @GetMapping("/me")
    ApiResponse<ProfileResponse> getMyProfile() {
        return ApiResponse.<ProfileResponse>builder()
                .result(profileService.getMyProfile())
                .build();
    }

    @PutMapping("/avatar")
    ApiResponse<ProfileResponse> updateAvatar(@RequestBody AvatarUpdateRequest request) {
        return ApiResponse.<ProfileResponse>builder()
                .result(profileService.updateAvatar(request))
                .build();
    }

}
