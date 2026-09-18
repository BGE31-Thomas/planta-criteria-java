package com.java.planta_criteria.register;

import com.java.planta_criteria.register.dto.RegistrationDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class RegistrationController {

    private final RegistrationService registrationService;

    public RegistrationController(
        RegistrationService registrationService
    ) {
        this.registrationService = registrationService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(
        @RequestBody RegistrationDto dto
    ) {
        System.out.println(">>> REGISTER CONTROLLER ATTEINT");
        registrationService.register(dto);
    }

    
}