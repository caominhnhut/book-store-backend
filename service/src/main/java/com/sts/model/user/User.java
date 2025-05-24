package com.sts.model.user;

import java.util.List;

import com.sts.util.enums.UserStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class User{

    private Long id;

    private String phoneNumber;

    private String email;

    private String fullName;

    private String password;

    private UserStatus status;

    private List<Long> roleIds;
}
