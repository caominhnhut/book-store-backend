package com.sts.dto.authentication.response;

import lombok.*;

import com.sts.util.enums.UserStatus;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class UserResponse{

    private Long id;

    private String phoneNumber;

    private String email;

    private String fullName;

    private String password;

    private UserStatus status;

}
