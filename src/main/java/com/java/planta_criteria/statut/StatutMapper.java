package com.java.planta_criteria.statut;

@Component
public class StatutMapper {

    public StatutDto toSearchDto(Statut statut) {
        return new StatutDto(
            statut.getId(),
            statut.getLibelle()
        );
    }
}
