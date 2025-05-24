package com.sts.model.userrole;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserRoleUpdateRequest {

    @NotNull
    private Long userId;

    @NotNull
    private Long roleId;

}
