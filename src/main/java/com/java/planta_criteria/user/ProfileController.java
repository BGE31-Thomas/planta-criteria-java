package com.java.planta_criteria.user;

import com.java.planta_criteria.user.dto.UserDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@PreAuthorize("hasRole('USER')")
public class ProfileController {

    private final UserService userService;

    public ProfileController(
        UserService userService
    ) {
        this.userService = userService;
    }

    @GetMapping
    public UserDto getProfile(
        Authentication authentication
    ) {
        return userService.findDtoByEmail(
            authentication.getName()
        );
    }
}