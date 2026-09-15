package com.java.planta_criteria.critere;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.planta_criteria.taxref.Taxref;

import java.util.List;

public interface CritereRepository extends JpaRepository<Critere, Long> {

    List<Critere> findByPlante(Taxref plante);
}
