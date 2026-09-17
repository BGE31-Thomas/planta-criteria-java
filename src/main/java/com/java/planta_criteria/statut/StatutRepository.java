package com.java.planta_criteria.statut;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StatutRepository extends JpaRepository<Statut, Integer> {

    Optional<Statut> findByLibelle(String libelle);
}
