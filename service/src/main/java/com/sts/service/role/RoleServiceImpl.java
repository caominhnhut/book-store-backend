package com.sts.service.role;

import com.sts.entity.RoleEntity;
import com.sts.model.role.Role;
import com.sts.model.role.RoleCreateRequest;
import com.sts.model.role.RoleUpdateRequest;
import com.sts.repository.RoleRepository;
import com.sts.service.mapper.RoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    private final RoleMapper roleMapper;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll().stream()
                .map(roleMapper::toDto)
                .toList();
    }

    @Override
    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id)
                .map(roleMapper::toDto);
    }

    @Override
    public Role createRole(RoleCreateRequest roleRequest) {
        var roleEntity = RoleEntity.builder()
                .roleName(roleRequest.getName())
                .build();
        var savedRole = roleRepository.save(roleEntity);
        return roleMapper.toDto(savedRole);
    }

    @Override
    public Role updateRole(Long id, RoleUpdateRequest roleDetails) {
        return roleRepository.findById(id)
                .map(existingRole -> {
                    var updatedRole = RoleEntity.builder()
                            .id(existingRole.getId())
                            .roleName(roleDetails.getName())
                            .build();
                    return roleMapper.toDto(roleRepository.save(updatedRole));
                })
                .orElseThrow(() -> new RuntimeException("Role not found"));
    }

    @Override
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }

}
