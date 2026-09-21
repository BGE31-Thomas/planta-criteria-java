package com.java.planta_criteria.critere;

import org.springframework.stereotype.Component;

import com.java.planta_criteria.critere.dto.CritereDto;
import com.java.planta_criteria.image.ImageMapper;
import com.java.planta_criteria.source.SourceMapper;

import com.java.planta_criteria.critere.CritereMapper;

import com.java.planta_criteria.taxref.TaxrefMapper;

@Component
public class CritereMapper {

    private final ImageMapper imageMapper;
    private final SourceMapper sourceMapper;
    private final TaxrefMapper taxrefMapper;

    public CritereMapper(ImageMapper imageMapper, SourceMapper sourceMapper, TaxrefMapper taxrefMapper) {
        this.imageMapper = imageMapper;
        this.sourceMapper = sourceMapper;
        this.taxrefMapper = taxrefMapper;
    }

    public CritereDto toSearchDto(Critere critere) {
        return new CritereDto(
            critere.getId(),
            critere.getOrgane(),
            critere.getDescription(),
            sourceMapper.toSearchDto(critere.getSource()),
            taxrefMapper.toSearchDto(critere.getPlante()),
            critere.getImages()
                .stream()
                .map(imageMapper::toSearchDto)
                .toList()
        );
    }

}

