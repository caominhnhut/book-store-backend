package com.sts.model.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserCreateRequest {

    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    private String email;

    private String fullName;

    @NotBlank(message = "Password is required")
    private String password;

}