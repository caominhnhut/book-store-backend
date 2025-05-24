package com.sts.resource;

import com.sts.model.role.Role;
import com.sts.model.role.RoleCreateRequest;
import com.sts.model.role.RoleUpdateRequest;
import com.sts.service.role.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
@Slf4j
public class RoleResource {

    private final RoleService roleService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MODERATOR')")
    /*
     * When you use hasRole('ADMIN'), Spring Security automatically adds the ROLE_ prefix,
     * so it checks for ROLE_ADMIN behind the scenes. If you want to use a custom prefix or no prefix at all,
     * you can use hasAuthority('ADMIN') instead.
     * */
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Role> getRoleById(@PathVariable Long id) {
        Optional<Role> role = roleService.getRoleById(id);
        return role.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Role> createRole(@RequestBody @Valid RoleCreateRequest roleCreateRequest) {
        Role createdRole = roleService.createRole(roleCreateRequest);
        return ResponseEntity.ok(createdRole);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> updateRole(@PathVariable Long id, @RequestBody @Valid RoleUpdateRequest roleUpdateRequest) {
        Role updatedRole = roleService.updateRole(id, roleUpdateRequest);
        return ResponseEntity.ok(updatedRole);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable Long id) {
        roleService.deleteRole(id);
        return ResponseEntity.noContent().build();
    }

}
