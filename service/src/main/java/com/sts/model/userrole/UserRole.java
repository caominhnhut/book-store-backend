package com.sts.model.userrole;

import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRole{

    private Long id;

    private Long userId;

    private Long roleId;

}
