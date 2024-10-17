package org.vetcabinet.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.vetcabinet.user.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public boolean checkPassword(final String passwordToCheck,
                                 final UserDetails userDetails) {
        return passwordEncoder.matches(passwordToCheck, userDetails.getPassword());
    }

}
