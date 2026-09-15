package com.java.planta_criteria.commune;

import com.java.planta_criteria.commune.dto.CommuneSearchDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class CommuneService {

    private final CommuneRepository communeRepository;
    private final CommuneMapper communeMapper;

    public CommuneService(
        CommuneRepository communeRepository,
        CommuneMapper communeMapper
    ) {
        this.communeRepository = communeRepository;
        this.communeMapper = communeMapper;
    }

    public List<CommuneSearchDto> search(String query) {

        if (query == null || query.length() < 2) {
            return List.of();
        }

        return communeRepository
            .findTop10ByNomContainingIgnoreCase(query)
            .stream()
            .map(communeMapper::toSearchDto)
            .toList();
    }
}