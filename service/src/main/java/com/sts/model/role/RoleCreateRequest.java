package com.sts.model.role;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleCreateRequest {

    @NotBlank(message = "Name is required")
    private String name;

}
