package org.vetcabinet.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.vetcabinet.exception.AlreadyExistsException;
import org.vetcabinet.user.dto.RegisterUserDto;
import org.vetcabinet.user.mapper.AddressMapper;
import org.vetcabinet.user.mapper.UserMapper;
import org.vetcabinet.user.model.Role;
import org.vetcabinet.user.model.RoleName;
import org.vetcabinet.user.model.User;
import org.vetcabinet.user.repository.*;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleRepository roleRepository;
    private final AddressRepository addressRepository;
    private final AddressMapper addressMapper;

    public boolean checkPassword(final String passwordToCheck,
                                 final UserDetails userDetails) {
        return passwordEncoder.matches(passwordToCheck, userDetails.getPassword());
    }

    @Transactional
    public RegisterUserDto create(RegisterUserDto registerUserDto) {
        User user = userMapper.toUser(registerUserDto);
        String login = registerUserDto.getLogin();
        if (userRepository.existsByLogin(login)) {
            throw new AlreadyExistsException("Логин " + login + " уже занят, выберите другой логин.");
        }
        Role role = roleRepository.findByName(RoleName.USER.name());
        user.setPassword(passwordEncoder.encode(registerUserDto.getPassword()));
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