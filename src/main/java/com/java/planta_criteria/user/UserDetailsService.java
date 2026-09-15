package com.java.planta_criteria.security;

import com.java.planta_criteria.user.User;
import com.java.planta_criteria.user.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(
        UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email)
        throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new UsernameNotFoundException(
                    "Utilisateur introuvable : " + email
                )
            );

        return org.springframework.security.core.userdetails.User.builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .authorities(
                user.getRoles()
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList()
            )
            .accountLocked(false)
            .disabled(!user.isVerified())
            .build();
    }
}
