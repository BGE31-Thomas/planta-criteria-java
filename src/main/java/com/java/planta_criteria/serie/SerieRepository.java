package com.java.planta_criteria.serie;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SerieRepository extends JpaRepository<Serie, Integer> {
    Optional<Serie> findByIdAndUserEmail(
        Integer id,
        String userEmail
    );

    List<Serie> findAllByUserEmail(String email);
}
