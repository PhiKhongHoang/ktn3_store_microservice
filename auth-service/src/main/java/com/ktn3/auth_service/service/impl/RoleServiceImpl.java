package com.ktn3.auth_service.service.impl;

import com.ktn3.auth_service.dto.request.RoleRequest;
import com.ktn3.auth_service.dto.response.RoleResponse;
import com.ktn3.auth_service.mapper.RoleMapper;
import com.ktn3.auth_service.repository.RoleRepository;
import com.ktn3.auth_service.service.RoleService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class RoleServiceImpl implements RoleService {
    RoleRepository roleRepository;
    RoleMapper roleMapper;

    @Override
    public RoleResponse create(RoleRequest request) {
        var role = roleMapper.toRole(request);

        role = roleRepository.save(role);
        return roleMapper.toRoleResponse(role);
    }

    @Override
    public List<RoleResponse> getAll() {
        return roleRepository.findAll().stream().map(roleMapper::toRoleResponse).toList();
    }

    @Override
    public void delete(String roleId) {
        roleRepository.deleteById(roleId);
    }
}
