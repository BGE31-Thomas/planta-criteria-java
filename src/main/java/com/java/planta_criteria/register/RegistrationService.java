package com.java.planta_criteria.register;

import com.java.planta_criteria.register.dto.RegistrationDto;
import com.java.planta_criteria.user.User;
import com.java.planta_criteria.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.planta_criteria.auth.EmailAlreadyUsedException;

import java.util.HashSet;

import java.util.Set;

@Service
public class RegistrationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(
        UserRepository userRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User register(RegistrationDto dto) {

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyUsedException(
                dto.getEmail()
            );
        }

        User user = new User();

        user.setEmail(dto.getEmail());
        user.setUsername(dto.getUsername());

        user.setPassword(
            passwordEncoder.encode(dto.getPassword())
        );

        user.setVerified(false);

        user.setRoles(
            new HashSet<>(Set.of("ROLE_USER"))
        );

        return userRepository.save(user);
    }
}
