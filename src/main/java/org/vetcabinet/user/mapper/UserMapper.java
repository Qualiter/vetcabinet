package org.vetcabinet.user.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.user.dto.RegisterUserDto;
import org.vetcabinet.user.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    RegisterUserDto toRegisterUserDTO(User user);

    User toUser(RegisterUserDto registerUserDTO);
}