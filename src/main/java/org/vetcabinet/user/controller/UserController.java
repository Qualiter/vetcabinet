package org.vetcabinet.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.vetcabinet.config.jwt.JWTTokenUtil;
import org.vetcabinet.user.dto.LoginDTO;
import org.vetcabinet.user.service.CustomUserDetailsService;
import org.vetcabinet.user.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;
    private final JWTTokenUtil tokenUtil;

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody LoginDTO loginDTO) {
        Map<String, Object> response = new HashMap<>();
        UserDetails foundUser = customUserDetailsService.loadUserByUsername(loginDTO.getLogin());
        log.info("User found: {}", foundUser);
        if (!userService.checkPassword(loginDTO.getPassword(), foundUser)) {
            return ResponseEntity.status((HttpStatus.UNAUTHORIZED)).body("Ошибка авторизации! Неверный пароль.");
        }
        final String token = tokenUtil.generateToken(foundUser);
        response.put("token", token);
        response.put("username", foundUser.getUsername());
        response.put("roles", foundUser.getAuthorities());
        return ResponseEntity.ok().body(response);

    }
}