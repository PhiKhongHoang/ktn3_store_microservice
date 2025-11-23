package com.ktn3.gateway_service.service;

import com.ktn3.gateway_service.dto.ApiResponse;
import com.ktn3.gateway_service.dto.request.IntrospectRequest;
import com.ktn3.gateway_service.dto.response.IntrospectResponse;
import com.ktn3.gateway_service.repository.IdentityClient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {
    IdentityClient identityClient;

    public Mono<ApiResponse<IntrospectResponse>> introspect(String token){
        return identityClient.introspect(IntrospectRequest.builder()
                        .token(token)
                .build());
    }
}
