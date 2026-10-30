package com.java.planta_criteria.critere;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.java.planta_criteria.taxref.Taxref;

import java.util.List;

public interface CritereRepository extends JpaRepository<Critere, Integer> {

    @EntityGraph(attributePaths = {"images", "source"})
    List<Critere> findByPlante(Taxref plante);
}
