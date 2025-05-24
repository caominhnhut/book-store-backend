package com.sts.model.user;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateRequest {

    @NotBlank(message = "Name is required")
    private String fullName;

}
