package com.java.planta_criteria.taxref;

import com.java.planta_criteria.taxref.dto.PlantDto;
import com.java.planta_criteria.taxref.dto.PlantSearchDto;
import com.java.planta_criteria.utils.SlugUtils;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

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

        System.out.println(">>> SEARCH ATTEINT");
        System.out.println(">>> q = " + query);

        List<PlantSearchDto> results = taxrefRepository
            .findTop20ByLbNomContainingIgnoreCase(query)
            .stream()
            .map(taxrefMapper::toSearchDto)
            .toList();
        System.out.println(">>> RÉSULTATS : " + results.size());
        return results;
    }

    public PlantDto findById(Integer id) {

        Taxref taxref = taxrefRepository.findById(Objects.requireNonNull(
            id,
            "L'identifiant de la plante ne peut pas être null"
        ))
        .orElseThrow(() ->
            new TaxrefNotFoundException(id)
        );

        return taxrefMapper.toDto(taxref);
    }

    public PlantDto findByIdOrValidTaxon(Integer id, String slug) {

        Taxref taxref = taxrefRepository.findById(Objects.requireNonNull(
            id,
            "L'identifiant de la plante ne peut pas être null"
        ))
        .orElseThrow(() ->
            new TaxrefNotFoundException(id)
        );

        if(SlugUtils.toSlug(taxref.getNomCompletHtml()).equals(slug) == false) {
            System.out.println(">>> nomCompletHtml : " + taxref.getNomCompletHtml());
            System.out.println(">>> SLUGS : " + SlugUtils.toSlug(taxref.getNomCompletHtml()) + " / " + slug);
            throw new IllegalArgumentException("Le slug fourni ne correspond pas au nom vernaculaire de la plante");
        }

        if (!taxref.getCdNom().equals(taxref.getCdRef())) {
            taxref = taxref.getNomValide();
        }

        return taxrefMapper.toDto(taxref);
    }
}