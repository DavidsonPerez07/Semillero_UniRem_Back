package com.unirem.auth_service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.unirem.auth_service.DTO.UserDTO;
import com.unirem.auth_service.entity.User;
//Mapper is not used because MapStruct had some conflicts
@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserDTO toDTO(User user);
    User toEntity(UserDTO userDTO);
}
