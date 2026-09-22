package com.java.planta_criteria.register;

import com.java.planta_criteria.email.EmailVerificationService;
import com.java.planta_criteria.email.ResendVerificationRequest;
import com.java.planta_criteria.register.dto.RegistrationDto;
import com.java.planta_criteria.user.UserRepository;
import com.java.planta_criteria.user.Users;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailVerificationService emailVerificationService;

    public RegistrationController(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            EmailVerificationService emailVerificationService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.emailVerificationService = emailVerificationService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegistrationDto request
    ) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body("Cette adresse email est déjà utilisée.");
        }
        System.out.println("ok");
        Users user = new Users();

        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());

        user.setPassword(
            passwordEncoder.encode(request.getPassword())
        );

        user.setVerified(false);

        // Équivalent du ROLE_USER Symfony
        user.getRoles().add("ROLE_USER");

        userRepository.save(user);

        emailVerificationService.sendVerificationEmail(user);

        return ResponseEntity.ok(
            "Compte créé. Un email de confirmation vous a été envoyé."
        );
    }

    @GetMapping("/verify/{token}")
    public ResponseEntity<String> verifyUser(
            @PathVariable String token
    ) {

        boolean verified =
                emailVerificationService.verify(token);

        if (!verified) {
            return ResponseEntity
                    .badRequest()
                    .body("Le token est invalide ou a expiré.");
        }

        return ResponseEntity.ok(
                "Votre compte a été activé."
        );
    }

    @PostMapping("/resend-verification")
    public ResponseEntity<String> resendVerification(
            @RequestBody ResendVerificationRequest request
    ) {

        userRepository.findByEmail(request.email())
            .ifPresent(user -> {

                if (!user.isVerified()) {
                    emailVerificationService
                        .sendVerificationEmail(user);
                }
            });

        return ResponseEntity.ok(
            "Si cette adresse correspond à un compte non vérifié, "
            + "un email de confirmation a été envoyé."
        );
    }
}