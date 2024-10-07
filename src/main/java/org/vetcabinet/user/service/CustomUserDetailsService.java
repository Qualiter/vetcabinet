package org.vetcabinet.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.vetcabinet.user.UserRepository;
import org.vetcabinet.user.model.User;
@Service
@Slf4j
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    @Value("${spring.security.user.name}")
    private String adminUser;
    @Value("${spring.security.user.password}")
    private String adminPassword;
    @Value("${spring.security.user.roles}")
    private String adminRole;
    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       if (username.equals(adminUser)) {
           //return new CustomUserDetails(null, adminUser, adminPassword, List.of(new SimpleGrantedAuthority("ROLE_" + adminRole)));
       } else {
           User user = userRepository.findUserByLogin(username).orElseThrow(() -> {
               log.error("User with such username: {} not found", username);
               return new UsernameNotFoundException("User with such username " + username + " not found");
           });
           //return new CustomUserDetails(user);
       }
       return null;
    }
}