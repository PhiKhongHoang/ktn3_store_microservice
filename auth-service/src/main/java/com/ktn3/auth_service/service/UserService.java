package com.ktn3.auth_service.service;

import com.ktn3.auth_service.dto.request.UserCreationRequest;
import com.ktn3.auth_service.dto.request.UserUpdateRequest;
import com.ktn3.auth_service.dto.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse createUser(UserCreationRequest request);
    UserResponse getMyInfo();
    UserResponse updateUser(String userId, UserUpdateRequest request);
    void deleteUser(String userId);
    List<UserResponse> getUsers();
    UserResponse getUser(String id);
}
