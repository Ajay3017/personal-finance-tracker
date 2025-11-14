package com.project.finance.util;

import com.project.finance.dto.User;
import com.project.finance.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    public UserEntity getUserEntity(User user);

    @InheritInverseConfiguration
    public User getUser(UserEntity userEntity);
}
