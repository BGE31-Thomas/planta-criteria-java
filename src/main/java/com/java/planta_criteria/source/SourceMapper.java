package com.java.planta_criteria.source;

import org.springframework.stereotype.Component;

import com.java.planta_criteria.source.dto.SourceDto;

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
