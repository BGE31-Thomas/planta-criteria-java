package com.java.planta_criteria.statut;

import com.java.planta_criteria.statut.dto.StatutDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class StatutService {

    private final StatutRepository statutRepository;
    private final StatutMapper statutMapper;

    public StatutService(
        StatutRepository statutRepository,
        StatutMapper statutMapper
    ) {
        this.statutRepository = statutRepository;
        this.statutMapper = statutMapper;
    }

    public List<StatutDto> findAll() {

        return statutRepository.findAll()
            .stream()
            .map(statutMapper::toSearchDto)
            .toList();
    }

    public StatutDto findById(Integer id) {

        Statut statut = statutRepository.findById(id)
            .orElseThrow(() ->
                new StatutNotFoundException(id)
            );

        return statutMapper.toSearchDto(statut);
    }

    public StatutDto findByLibelle(String libelle) {

        Statut statut = statutRepository.findByLibelle(libelle)
            .orElseThrow(() ->
                new StatutNotFoundException(libelle)
            );

        return statutMapper.toSearchDto(statut);
    }
}
