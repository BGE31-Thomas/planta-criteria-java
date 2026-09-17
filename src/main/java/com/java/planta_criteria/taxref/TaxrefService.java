package com.java.planta_criteria.taxref;

import com.java.planta_criteria.taxref.dto.PlantDto;
import com.java.planta_criteria.taxref.dto.PlantSearchDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TaxrefService {

    private final TaxrefRepository taxrefRepository;
    private final TaxrefMapper taxrefMapper;

    public TaxrefService(
        TaxrefRepository taxrefRepository,
        TaxrefMapper taxrefMapper
    ) {
        this.taxrefRepository = taxrefRepository;
        this.taxrefMapper = taxrefMapper;
    }

    public List<PlantSearchDto> search(String query) {

        if (query == null || query.isBlank()) {
            return List.of();
        }

        return taxrefRepository
            .findTop20ByLbNomContainingIgnoreCase(query)
            .stream()
            .map(taxrefMapper::toSearchDto)
            .toList();
    }

    public PlantDto findById(Integer id) {

        Taxref taxref = taxrefRepository.findById(id)
            .orElseThrow(() ->
                new TaxrefNotFoundException(id)
            );

        return taxrefMapper.toDto(taxref);
    }

    public PlantDto findByIdOrValidTaxon(Integer id) {

        Taxref taxref = taxrefRepository.findById(id)
            .orElseThrow(() ->
                new TaxrefNotFoundException(id)
            );

        if (!taxref.getCdNom().equals(taxref.getCdRef())) {
            taxref = taxref.getNomValide();
        }

        return taxrefMapper.toDto(taxref);
    }
}