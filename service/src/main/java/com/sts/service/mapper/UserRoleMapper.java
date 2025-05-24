package com.sts.service.mapper;

import com.sts.entity.UserRoleEntity;
import com.sts.model.userrole.UserRole;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserRoleMapper {

    UserRole toDto(UserRoleEntity userRoleEntity);

}
