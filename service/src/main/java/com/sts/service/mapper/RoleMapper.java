package com.sts.service.mapper;

import com.sts.entity.RoleEntity;
import com.sts.model.role.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    Role toDto(RoleEntity roleEntity);

}
