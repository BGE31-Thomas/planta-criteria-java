package com.java.planta_criteria.commune;

import org.springframework.stereotype.Component;

import com.java.planta_criteria.commune.dto.CommuneSearchDto;

@Component
public class CommuneMapper {

    public CommuneSearchDto toSearchDto(Commune commune) {
        return new CommuneSearchDto(
            commune.getId(),
            commune.getCode_postal() + " " + commune.getNom()
        );
    }

}

    


