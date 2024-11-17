package org.vetcabinet.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vetcabinet.user.dto.AuthResponseDto;
import org.vetcabinet.user.dto.LoginDto;
import org.vetcabinet.user.dto.RegisterUserDto;
import org.vetcabinet.user.service.UserServiceImpl;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    // TODO: Log out, switch
    private final UserServiceImpl userService;

    @PostMapping("/auth")
    public AuthResponseDto auth(@RequestBody LoginDto loginDto) {
        return userService.auth(loginDto);
    }

    @PostMapping("/register")
    public RegisterUserDto register(@RequestBody RegisterUserDto registerUserDTO) {
        return userService.create(registerUserDTO);
    }
}