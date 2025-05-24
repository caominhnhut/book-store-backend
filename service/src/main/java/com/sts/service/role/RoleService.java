package com.sts.service.role;

import com.sts.model.role.Role;
import com.sts.model.role.RoleCreateRequest;
import com.sts.model.role.RoleUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface RoleService {

    List<Role> getAllRoles();

    Optional<Role> getRoleById(Long id);

    Role createRole(RoleCreateRequest role);

    Role updateRole(Long id, RoleUpdateRequest roleDetails);

    void deleteRole(Long id);

}
