package com.ktn3.auth_service.repository.httpclient;

import com.ktn3.auth_service.configuration.AuthenticationRequestInterceptor;
import com.ktn3.auth_service.dto.ApiResponse;
import com.ktn3.auth_service.dto.request.ProfileCreationRequest;
import com.ktn3.auth_service.dto.response.ProfileResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "profile-service", url = "${app.services.profile}",
        configuration = { AuthenticationRequestInterceptor.class })
public interface ProfileClient {
    @PostMapping(value = "internal", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<ProfileResponse> createProfile(@RequestBody ProfileCreationRequest request);
}