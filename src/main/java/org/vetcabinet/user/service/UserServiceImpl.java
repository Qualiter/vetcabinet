package org.vetcabinet.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vetcabinet.address.mapper.AddressMapper;
import org.vetcabinet.config.jwt.JWTTokenUtil;
import org.vetcabinet.exception.AlreadyExistsException;
import org.vetcabinet.exception.AuthorizationException;
import org.vetcabinet.user.dto.AuthResponseDto;
import org.vetcabinet.user.dto.LoginDto;
import org.vetcabinet.user.dto.RegisterUserDto;
import org.vetcabinet.user.mapper.EmailMapper;
import org.vetcabinet.user.mapper.PhoneMapper;
import org.vetcabinet.user.mapper.UserMapper;
import org.vetcabinet.user.model.Role;
import org.vetcabinet.user.model.RoleName;
import org.vetcabinet.user.model.User;
import org.vetcabinet.user.repository.EmailRepository;
import org.vetcabinet.user.repository.PhoneRepository;
import org.vetcabinet.user.repository.RoleRepository;
import org.vetcabinet.user.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {
    private final CustomUserDetailsService customUserDetailsService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final EmailRepository emailRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final AddressMapper addressMapper;
    private final PhoneMapper phoneMapper;
    private final EmailMapper emailMapper;
    private final JWTTokenUtil tokenUtil;

    @Transactional(readOnly = true)
    public boolean checkPassword(final String passwordToCheck,
                                 final UserDetails userDetails) {
        return passwordEncoder.matches(passwordToCheck, userDetails.getPassword());
    }
    //TODO: private? not allowed in interface

    @Transactional(readOnly = true)
    public AuthResponseDto auth(LoginDto loginDTO) {
        UserDetails foundUser = customUserDetailsService.loadUserByUsername(loginDTO.getLogin());
        log.info("User found: {}", foundUser);
        if (!checkPassword(loginDTO.getPassword(), foundUser)) {
            throw new AuthorizationException("Ошибка авторизации! Неверный пароль.");
        }
        final String token = tokenUtil.generateToken(foundUser);
        return new AuthResponseDto(token, foundUser.getUsername(), foundUser.getAuthorities());
    }

    @Transactional
    public RegisterUserDto create(RegisterUserDto registerUserDto) {
        User user = userMapper.toUser(registerUserDto);
        String login = registerUserDto.getLogin();
        if (userRepository.existsByLogin(login)) {
            throw new AlreadyExistsException("Логин " + login + " уже занят, выберите другой.");
        }
        Role role = roleRepository.findByName(RoleName.USER.name());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
        // TODO: check this
        //user.setEmail(emailRepository.save(emailMapper.toListEmail(registerUserDto.getEmail())));
        //user.setPhone(phoneRepository.save(phoneMapper.toListPhone(registerUserDto.getPhone())));
        user.setRole(role);
        user.setIsActive(true);
        user.setRegistrationDate(LocalDateTime.now());
        user.setAddresses(registerUserDto.getAddresses().stream()
                .map(addressMapper::toAddress)
                .collect(Collectors.toList()));

        User savedUser = userRepository.save(user);
        return userMapper.toRegisterUserDTO(savedUser);
    }
}