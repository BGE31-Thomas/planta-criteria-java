package com.java.planta_criteria.observation;

import com.java.planta_criteria.observation.dto.ObservationDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ObservationService {

    private final ObservationRepository observationRepository;
    private final ObservationMapper observationMapper;

    public ObservationService(
        ObservationRepository observationRepository,
        ObservationMapper observationMapper
    ) {
        this.observationRepository = observationRepository;
        this.observationMapper = observationMapper;
    }

    public List<ObservationDto> findAll() {

        return observationRepository.findAll()
            .stream()
            .map(observationMapper::toSearchDto)
            .toList();
    }

    public ObservationDto findById(Long id) {

        Observation observation = observationRepository.findById(id)
            .orElseThrow(() ->
                new ObservationNotFoundException(id)
            );

        return observationMapper.toSearchDto(observation);
    }
}
