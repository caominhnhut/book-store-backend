package com.sts.service.userrole;

import com.sts.model.userrole.UserRole;
import com.sts.model.userrole.UserRoleCreateRequest;
import com.sts.model.userrole.UserRoleUpdateRequest;

import java.util.List;
import java.util.Optional;

public interface UserRoleService {

    List<UserRole> getAllUserRoles();

    Optional<UserRole> getUserRoleById(Long id);

    UserRole createUserRole(UserRoleCreateRequest userRoleRequest);

    UserRole updateUserRole(Long id, UserRoleUpdateRequest userRoleDetails);

    void deleteUserRole(Long id);

}
