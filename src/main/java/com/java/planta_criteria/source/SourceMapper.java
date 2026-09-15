package com.java.planta_criteria.source;

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
