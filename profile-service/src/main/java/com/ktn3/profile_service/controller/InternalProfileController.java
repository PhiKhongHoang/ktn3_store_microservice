package com.ktn3.profile_service.controller;

import com.ktn3.profile_service.dto.ApiResponse;
import com.ktn3.profile_service.dto.request.ProfileCreationRequest;
import com.ktn3.profile_service.dto.response.ProfileResponse;
import com.ktn3.profile_service.service.ProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequestMapping("/internal")
public class InternalProfileController {
    ProfileService userProfileService;

    @PostMapping
    ApiResponse<ProfileResponse> createProfile(@RequestBody ProfileCreationRequest request) {
        return ApiResponse.<ProfileResponse>builder()
                .result(userProfileService.createProfile(request))
                .build();
    }

    @GetMapping("/{userId}")
    ApiResponse<ProfileResponse> getProfile(@PathVariable String userId) {
        return ApiResponse.<ProfileResponse>builder()
                .result(userProfileService.getByUserId(userId))
                .build();
    }

}
