package com.sts.service.user;

import java.io.Serial;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.sts.entity.RoleEntity;
import com.sts.entity.UserEntity;
import com.sts.util.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Slf4j
public class UserDetailsImpl implements UserDetails {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    private String email;

    private String password;

    private UserStatus status;

    private Collection<? extends GrantedAuthority> authorities;

    public static UserDetailsImpl build(UserEntity userEntity, Set<RoleEntity> roleEntities) {
        var authorities = roleEntities.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .toList();

        return UserDetailsImpl.builder()
                .id(userEntity.getId())
                .name(userEntity.getFullName())
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .status(userEntity.getStatus())
                .authorities(authorities)
                .build();
    }

    @Override
    public String getUsername() {
        return getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return status == UserStatus.ACTIVE;
    }

}
