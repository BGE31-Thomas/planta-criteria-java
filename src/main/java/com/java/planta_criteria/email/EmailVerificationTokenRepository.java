package com.java.planta_criteria.email;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.planta_criteria.user.Users;

public interface EmailVerificationTokenRepository
        extends JpaRepository<EmailVerificationToken, Long> {

    Optional<EmailVerificationToken> findByToken(String token);

    void deleteByUser(Users user);
}
