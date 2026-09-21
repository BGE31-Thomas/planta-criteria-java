package com.java.planta_criteria.statut;

import org.springframework.stereotype.Component;

import com.java.planta_criteria.statut.dto.StatutDto;

@Component
public class StatutMapper {

    public StatutDto toSearchDto(Statut statut) {
        return new StatutDto(
            statut.getId(),
            statut.getLibelle()
        );
    }
}
