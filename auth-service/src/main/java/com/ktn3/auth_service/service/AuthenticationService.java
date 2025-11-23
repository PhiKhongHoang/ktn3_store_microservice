package com.ktn3.auth_service.service;

import com.ktn3.auth_service.dto.request.AuthenticationRequest;
import com.ktn3.auth_service.dto.request.IntrospectRequest;
import com.ktn3.auth_service.dto.request.LogoutRequest;
import com.ktn3.auth_service.dto.request.RefreshRequest;
import com.ktn3.auth_service.dto.response.AuthenticationResponse;
import com.ktn3.auth_service.dto.response.IntrospectResponse;
import com.nimbusds.jose.JOSEException;

import java.text.ParseException;

public interface AuthenticationService {
    IntrospectResponse introspect(IntrospectRequest request);
    AuthenticationResponse authenticate(AuthenticationRequest request);
    void logout(LogoutRequest request) throws ParseException, JOSEException;
    AuthenticationResponse refreshToken(RefreshRequest request) throws ParseException, JOSEException;
}
