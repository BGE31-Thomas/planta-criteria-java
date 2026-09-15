package com.java.planta_criteria.user;

import com.java.planta_criteria.user.dto.UserDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserService(
        UserRepository userRepository,
        UserMapper userMapper
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Transactional(readOnly = true)
    public User findByEmail(String email) {

        return userRepository.findByEmail(email)
            .orElseThrow(() ->
                new UserNotFoundException(email)
            );
    }

    @Transactional(readOnly = true)
    public UserDto findDtoByEmail(String email) {

        return userMapper.toDto(
            findByEmail(email)
        );
    }

    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {

        return userRepository.existsByEmail(email);
    }
}