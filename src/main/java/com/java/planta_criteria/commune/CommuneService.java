package com.java.planta_criteria.commune;

import org.springframework.stereotype.Service;

import com.java.planta_criteria.commune.dto.CommuneSearchDto;

import java.util.Collections;
import java.util.List;

@Service
public class CommuneService {

    private final CommuneRepository communeRepository;

    public CommuneService(
        CommuneRepository communeRepository
    ) {
        this.communeRepository = communeRepository;
    }

    public List<CommuneSearchDto> search(String query) {

        if (query == null || query.length() < 2) {
            return Collections.emptyList();
        }

        return communeRepository
            .findTop10ByNomContainingIgnoreCase(query)
            .stream()
            .map(this::toDto)
            .toList();
    }

    private CommuneSearchDto toDto(Commune commune) {

        String text = commune.getNom()
            + " ("
            + commune.getCode_postal()
            + ")";

        return new CommuneSearchDto(
            commune.getId(),
            text
        );
    }
}
