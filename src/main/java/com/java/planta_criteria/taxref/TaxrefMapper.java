package com.java.planta_criteria.taxref;

import org.springframework.stereotype.Component;

import com.java.planta_criteria.taxref.dto.PlantDto;
import com.java.planta_criteria.taxref.dto.PlantSearchDto;

import com.java.planta_criteria.taxref.dto.TaxrefSummaryDto;

@Component
public class TaxrefMapper {

    private final CritereMapper critereMapper;
    private final TaxrefSummaryMapper taxrefSummaryMapper;

    public TaxrefMapper(CritereMapper critereMapper, TaxrefSummaryMapper taxrefSummaryMapper) {
        this.critereMapper = critereMapper;
        this.taxrefSummaryMapper = taxrefSummaryMapper;
    }

    public PlantSearchDto toSearchDto(Taxref taxref) {
        return new PlantSearchDto(
                taxref.getCdNom(),
                taxref.getNomCompletHtml()
        );
    }

    public TaxrefSummaryDto toTaxrefSummaryDto(Taxref taxref) {
        return new TaxrefSummaryDto(
                taxref.getCdNom(),
                taxref.getNom(),
                taxref.getLbNom()
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
                taxref.getCriteres()
                        .stream()
                        .map(critereMapper::toSearchDto)
                        .toList()
        );
    }
}
