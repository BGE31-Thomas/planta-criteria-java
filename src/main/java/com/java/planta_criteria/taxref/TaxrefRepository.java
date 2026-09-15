package com.java.planta_criteria.taxref;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaxrefRepository
    extends JpaRepository<Taxref, Long> {

    List<Taxref> findByCdNom(Long cdNom);

    List<Taxref> findTop20ByLbNomContainingIgnoreCase(String q);
}
