package com.java.planta_criteria.user;

import java.util.HashSet;
import java.util.Set;

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

        Users user = userRepository.findByEmail(email)
            .orElseThrow(() ->
                new UsernameNotFoundException(
                    "Utilisateur introuvable : " + email
                )
            );

        Set<String> roles = new HashSet<>(user.getRoles());
        roles.add("ROLE_USER");

        return org.springframework.security.core.userdetails.User
            .builder()
            .username(user.getEmail())
            .password(user.getPassword())
            .authorities(
                roles.stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList()
            )
            .accountLocked(false)
            .disabled(!user.isVerified())
            .build();
    }
}