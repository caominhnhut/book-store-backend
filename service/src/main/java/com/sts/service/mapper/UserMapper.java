package com.sts.service.mapper;

import com.sts.entity.UserEntity;
import com.sts.model.user.User;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User fromUserEntity(UserEntity userEntity);

    UserEntity toUserEntity(User user);
}
