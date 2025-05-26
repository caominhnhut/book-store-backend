package com.sts.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.sts.entity.UserEntity;
import com.sts.model.user.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromUserEntity(UserEntity userEntity);

    @Named("withoutPassword")
    @Mapping(target = "password", ignore = true)
    User fromUserEntityWithoutPassword(UserEntity userEntity);

    UserEntity toUserEntity(User user);
}
