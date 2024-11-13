package org.vetcabinet.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.vetcabinet.user.dto.RegisterUserDto;

public interface UserService {
    boolean checkPassword(final String passwordToCheck,
                          final UserDetails userDetails);

    RegisterUserDto create(RegisterUserDto registerUserDto);
}
