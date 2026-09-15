package com.java.planta_criteria.critere;

import org.springframework.stereotype.Component;

import com.java.planta_criteria.taxref.dto.PlantDto;
import com.java.planta_criteria.taxref.dto.PlantSearchDto;

import com.java.planta_criteria.taxref.dto.TaxrefSummaryDto;

import com.java.planta_criteria.critere.dto.CritereDto;
import com.java.planta_criteria.image.ImageMapper;
import com.java.planta_criteria.source.SourceMapper;

import com.java.planta_criteria.critere.CritereMapper;

@Component
public class CritereMapper {

    private final ImageMapper imageMapper;
    private final SourceMapper sourceMapper;

    public CritereMapper(ImageMapper imageMapper, SourceMapper sourceMapper) {
        this.imageMapper = imageMapper;
        this.sourceMapper = sourceMapper;
    }

    public CritereDto toSearchDto(Critere critere) {
        return new CritereDto(
            critere.getId(),
            critere.getOrgane(),
            critere.getDescription(),
            sourceMapper.toSearchDto(critere.getSource()),
            critere.getImages()
                .stream()
                .map(imageMapper::toSearchDto)
                .toList()
        );
    }

}

