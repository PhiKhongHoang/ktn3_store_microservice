package com.ktn3.auth_service.service;

import com.ktn3.auth_service.dto.request.RoleRequest;
import com.ktn3.auth_service.dto.response.RoleResponse;

import java.util.List;

public interface RoleService {
    RoleResponse create(RoleRequest request);
    List<RoleResponse> getAll();
    void delete(String role);
}
