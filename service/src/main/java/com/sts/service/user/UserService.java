package com.sts.service.user;

import java.util.List;
import java.util.Optional;

import com.sts.model.user.User;
import com.sts.model.user.UserUpdateRequest;

public interface UserService {

    List<User> getAllUsers();

    Optional<User> getUserById(Long id);

    User updateUser(Long id, UserUpdateRequest userDetails);

    void deleteUser(Long id);

    Optional<User> findByPhoneNumber(String phoneNumber);

    Optional<User> findByEmail(String email);

    Long register(User user);

    boolean verifyEmail(String token);

}
