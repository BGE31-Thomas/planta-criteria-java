package com.java.planta_criteria.commune;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommuneRepository extends JpaRepository<Commune, Integer> {

    List<Commune> findTop10ByNomContainingIgnoreCase(String nom);
}
