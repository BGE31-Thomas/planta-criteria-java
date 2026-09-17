package com.java.planta_criteria.observation_critere;

import com.java.planta_criteria.observation_critere.dto.ObservationCritereDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ObservationCritereService {

    private final ObservationCritereRepository repository;
    private final ObservationCritereMapper mapper;

    public ObservationCritereService(
        ObservationCritereRepository repository,
        ObservationCritereMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<ObservationCritereDto> findAll() {

        return repository.findAll()
            .stream()
            .map(mapper::toSearchDto)
            .toList();
    }

    public ObservationCritereDto findById(Integer id) {

        ObservationCritere observationCritere =
            repository.findById(id)
                .orElseThrow(() ->
                    new ObservationCritereNotFoundException(id)
                );

        return mapper.toSearchDto(observationCritere);
    }
}
