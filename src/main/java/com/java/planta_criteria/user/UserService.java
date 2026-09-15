package com.java.planta_criteria.user;

import com.java.planta_criteria.user.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User findByEmail(String email) {

        return userRepository.findByEmail(email)
            .orElseThrow(() ->
                new UserNotFoundException(email)
            );
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }
}
