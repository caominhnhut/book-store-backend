package com.sts.mapper;

import org.mapstruct.Mapper;

import com.sts.dto.register.request.SignupRequest;
import com.sts.model.user.User;

@Mapper(componentModel = "spring")
public interface UserResourceMapper{

    User toUser(SignupRequest signupRequest);
}
