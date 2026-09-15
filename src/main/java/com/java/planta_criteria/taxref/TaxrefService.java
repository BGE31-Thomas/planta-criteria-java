package com.java.planta_criteria.taxref;

import org.springframework.stereotype.Service;

import com.java.planta_criteria.taxref.dto.PlantSearchDto;

import java.util.List;

@Service
public class TaxrefService {

    private final TaxrefRepository taxrefRepository;

    public TaxrefService(
        TaxrefRepository taxrefRepository
    ) {
        this.taxrefRepository = taxrefRepository;
    }

    public List<PlantSearchDto> search(String query) {

        if (query == null || query.isBlank()) {
            return List.of();
        }

        return taxrefRepository
            .findTop20ByLbNomContainingIgnoreCase(query)
            .stream()
            .map(this::toSearchDto)
            .toList();
    }

    private PlantSearchDto toSearchDto(
        Taxref taxref
    ) {
        return new PlantSearchDto(
            taxref.getCdNom(),
            taxref.getNomCompletHtml()
        );
    }
}