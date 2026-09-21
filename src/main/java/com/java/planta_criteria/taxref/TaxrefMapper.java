package com.java.planta_criteria.taxref;

import org.springframework.stereotype.Component;

import com.java.planta_criteria.taxref.dto.PlantDto;
import com.java.planta_criteria.taxref.dto.PlantSearchDto;

import com.java.planta_criteria.critere.CritereMapper;

@Component
public class TaxrefMapper {

    private final TaxrefSummaryMapper taxrefSummaryMapper;

    public TaxrefMapper(TaxrefSummaryMapper taxrefSummaryMapper) {
        this.taxrefSummaryMapper = taxrefSummaryMapper;
    }

    public PlantSearchDto toSearchDto(Taxref taxref) {
        return new PlantSearchDto(
            taxref.getCdNom(),
            taxref.getNomCompletHtml()
        );
    }

    public PlantDto toDto(Taxref taxref) {
        return new PlantDto(
            taxref.getCdNom(),
            taxref.getNom(),
            taxref.getLbNom(),
            taxref.getNomCompletHtml(),
            taxref.getFamille(),
            taxref.getSynonymes()
                .stream()
                .map(taxrefSummaryMapper::toTaxrefSummaryDto)
                .toList(),
            null
        );
    }
}
