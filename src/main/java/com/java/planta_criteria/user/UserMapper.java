package com.java.planta_criteria.user;

import com.java.planta_criteria.user.dto.UserDto;
import org.springframework.stereotype.Component;
import com.java.planta_criteria.user.Users;

import java.util.HashSet;

@Component
public class UserMapper {

    public UserDto toDto(Users user) {

        return new UserDto(
            user.getId(),
            user.getEmail(),
            user.getUsername(),
            new HashSet<>(user.getRoles()),
            user.isVerified()
        );
    }
}
