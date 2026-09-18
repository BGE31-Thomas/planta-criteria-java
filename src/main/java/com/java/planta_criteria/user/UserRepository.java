package com.java.planta_criteria.user;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.java.planta_criteria.user.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {
    Optional<Users> findByEmail(String email);

    boolean existsByEmail(String email);
}
