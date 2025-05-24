package com.sts.service.userrole;

import com.sts.entity.UserRoleEntity;
import com.sts.model.userrole.UserRole;
import com.sts.model.userrole.UserRoleCreateRequest;
import com.sts.model.userrole.UserRoleUpdateRequest;
import com.sts.repository.UserRoleRepository;
import com.sts.service.mapper.UserRoleMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    private final UserRoleMapper userRoleMapper;

    @Override
    public List<UserRole> getAllUserRoles() {
        return userRoleRepository.findAll().stream()
                .map(userRoleMapper::toDto)
                .toList();
    }

    @Override
    public Optional<UserRole> getUserRoleById(Long id) {
        return userRoleRepository.findById(id)
                .map(userRoleMapper::toDto);
    }

    @Override
    public UserRole createUserRole(UserRoleCreateRequest userRoleRequest) {
        var userRoleEntity = UserRoleEntity.builder()
                .userId(userRoleRequest.getUserId())
                .roleId(userRoleRequest.getRoleId())
                .build();
        var savedUserRole = userRoleRepository.save(userRoleEntity);
        return userRoleMapper.toDto(savedUserRole);
    }

    @Override
    public UserRole updateUserRole(Long id, UserRoleUpdateRequest userRoleDetails) {
        return userRoleRepository.findById(id)
                .map(existingUserRole -> {
                    var updatedUserRole = UserRoleEntity.builder()
                            .id(existingUserRole.getId())
                            .userId(userRoleDetails.getUserId())
                            .roleId(userRoleDetails.getRoleId())
                            .build();
                    return userRoleMapper.toDto(userRoleRepository.save(updatedUserRole));
                })
                .orElseThrow(() -> new RuntimeException("UserRole not found"));
    }

    @Override
    public void deleteUserRole(Long id) {
        userRoleRepository.deleteById(id);
    }

}
