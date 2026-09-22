package com.java.planta_criteria.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.java.planta_criteria.user.Users;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendVerificationEmail(
            Users user,
            String token
    ) {

        String verificationUrl =
                "http://localhost:8000/api/auth/verify/" + token;

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setFrom("from@monsite.com");
        message.setTo(user.getEmail());
        message.setSubject("Activation de votre compte");

        message.setText("""
                Bonjour %s,

                Merci pour votre inscription sur Planta Criteria.

                Pour activer votre compte, cliquez sur le lien suivant :

                %s

                Ce lien est valable pendant 24 heures.

                Cordialement,
                Planta Criteria
                """.formatted(
                    user.getUsername(),
                    verificationUrl
                ));

        mailSender.send(message);
    }
}