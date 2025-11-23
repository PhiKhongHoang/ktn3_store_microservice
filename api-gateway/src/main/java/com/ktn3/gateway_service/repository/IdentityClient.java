package com.ktn3.gateway_service.repository;

import com.ktn3.gateway_service.dto.ApiResponse;
import com.ktn3.gateway_service.dto.request.IntrospectRequest;
import com.ktn3.gateway_service.dto.response.IntrospectResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

public interface IdentityClient {
    @PostExchange(url = "/auth/introspect", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<IntrospectResponse>> introspect(@RequestBody IntrospectRequest request);
}
