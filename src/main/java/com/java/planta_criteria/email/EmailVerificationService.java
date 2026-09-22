package com.java.planta_criteria.email;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.java.planta_criteria.user.Users;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class EmailVerificationService {

    private final EmailVerificationTokenRepository tokenRepository;
    private final EmailService emailService;

    public EmailVerificationService(
            EmailVerificationTokenRepository tokenRepository,
            EmailService emailService
    ) {
        this.tokenRepository = tokenRepository;
        this.emailService = emailService;
    }

    public void sendVerificationEmail(Users user) {

        // Supprime l'ancien token éventuel
        tokenRepository.deleteByUser(user);

        String token = UUID.randomUUID().toString();

        EmailVerificationToken verificationToken =
                new EmailVerificationToken(
                        user,
                        token,
                        LocalDateTime.now().plusHours(24)
                );

        tokenRepository.save(verificationToken);

        emailService.sendVerificationEmail(
                user,
                token
        );
    }

    public boolean verify(String token) {

        Optional<EmailVerificationToken> optional =
                tokenRepository.findByToken(token);

        if (optional.isEmpty()) {
            return false;
        }

        EmailVerificationToken verificationToken =
                optional.get();

        if (verificationToken.getExpiresAt()
                .isBefore(LocalDateTime.now())) {

            tokenRepository.delete(verificationToken);

            return false;
        }

        Users user = verificationToken.getUser();

        if (user.isVerified()) {
            return false;
        }

        user.setVerified(true);

        tokenRepository.delete(verificationToken);

        return true;
    }
}
