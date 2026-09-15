package com.java.planta_criteria.source;

import org.springframework.stereotype.Component;

import com.java.planta_criteria.source.dto.SourceDto;

import com.java.planta_criteria.source.Source;

@Component
public class SourceMapper {

    public SourceDto toSearchDto(Source source) {
        return new SourceDto(
            source.getId(),
            source.getTitre(),
            source.getAuteur(),
            source.getAnnee()
           
        );
    }
}
