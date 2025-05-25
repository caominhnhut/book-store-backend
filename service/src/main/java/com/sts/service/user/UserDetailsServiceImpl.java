package com.sts.service.user;

import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sts.repository.UserRepository;
import com.sts.repository.UserRoleRepository;
import com.sts.util.enums.UserStatus;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    private final UserRoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // UserDetailsImpl.isEnabled() will return false for non-ACTIVE users,
        // but let's throw a more specific exception for better error handling
        if (user.getStatus() != UserStatus.ACTIVE) {
            log.warn("Attempted login by inactive user with email: {}, status: {}", username, user.getStatus());
            throw new AuthorizationDeniedException("User account is not active");
        }

        var roles = userRoleRepository.findByUserId(user.getId());

        return UserDetailsImpl.build(user, roles);
    }
}
