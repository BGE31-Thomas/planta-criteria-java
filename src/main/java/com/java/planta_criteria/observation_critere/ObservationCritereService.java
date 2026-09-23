package com.java.planta_criteria.observation_critere;

import com.java.planta_criteria.critere.Critere;
import com.java.planta_criteria.critere.CritereNotFoundException;
import com.java.planta_criteria.critere.CritereRepository;

import com.java.planta_criteria.observation.Observation;
import com.java.planta_criteria.observation.ObservationNotFoundException;
import com.java.planta_criteria.observation.ObservationRepository;

import com.java.planta_criteria.observation_critere.dto.ObservationCritereCreateDto;
import com.java.planta_criteria.observation_critere.dto.ObservationCritereDto;
import com.java.planta_criteria.observation_critere.dto.ObservationCritereUpdateDto;

import com.java.planta_criteria.statut.Statut;
import com.java.planta_criteria.statut.StatutNotFoundException;
import com.java.planta_criteria.statut.StatutRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class ObservationCritereService {

    private final ObservationCritereRepository repository;
    private final ObservationCritereMapper mapper;
    private final CritereRepository critereRepository;
    private final StatutRepository statutRepository;
    private final ObservationRepository observationRepository;

    public ObservationCritereService(
        ObservationCritereRepository repository,
        ObservationCritereMapper mapper,
        CritereRepository critereRepository,
        StatutRepository statutRepository,
        ObservationRepository observationRepository
    ) {
        this.repository = repository;
        this.mapper = mapper;
        this.critereRepository = critereRepository;
        this.statutRepository = statutRepository;
        this.observationRepository = observationRepository;
    }

    public List<ObservationCritereDto> findAll() {

        return repository.findAll()
            .stream()
            .map(mapper::toSearchDto)
            .toList();
    }

    public ObservationCritereDto findById(Integer id) {

        ObservationCritere observationCritere =
            repository.findById(Objects.requireNonNull(
            id,
            "L'identifiant du critère ne peut pas être null"
        ))
                .orElseThrow(() ->
                    new ObservationCritereNotFoundException(id)
                );

        return mapper.toSearchDto(observationCritere);
    }

    @Transactional
    public ObservationCritereDto create(ObservationCritereCreateDto dto) {

        Critere critere = critereRepository.findById(Objects.requireNonNull(
            dto.critere_id(),
            "L'identifiant du critère ne peut pas être null"
        ))
            .orElseThrow(() ->
                new CritereNotFoundException(dto.critere_id())
            );

        Statut statut = statutRepository.findById(Objects.requireNonNull(
            dto.statut_id(),
            "L'identifiant du statut ne peut pas être null"
        ))
            .orElseThrow(() ->
                new StatutNotFoundException(dto.statut_id()
            ));

        Observation observation = observationRepository.findById(Objects.requireNonNull(
            dto.observation_id(),
            "L'identifiant de l'observation ne peut pas être null"
        ))
            .orElseThrow(() ->
                new ObservationNotFoundException(dto.observation_id())
            );

        ObservationCritere observationCritere = new ObservationCritere();
        observationCritere.setCritere(critere);
        observationCritere.setStatut(statut);
        observationCritere.setObservation(observation);
      
        ObservationCritere saved =
            repository.save(observationCritere);

        return mapper.toSearchDto(saved);
    }

    @Transactional
    public ObservationCritereDto update(Integer observation_critere_id, ObservationCritereUpdateDto dto) {

        Critere critere = critereRepository.findById(Objects.requireNonNull(
            dto.critere_id(),
            "L'identifiant du critère ne peut pas être null"
        ))
            .orElseThrow(() ->
                new CritereNotFoundException(dto.critere_id())
            );

        Statut statut = statutRepository.findById(Objects.requireNonNull(
            dto.statut_id(),
            "L'identifiant du statut ne peut pas être null"
        ))
            .orElseThrow(() ->
                new StatutNotFoundException(dto.statut_id()
            ));

        Observation observation = observationRepository.findById(Objects.requireNonNull(
            dto.observation_id(),
            "L'identifiant de l'observation ne peut pas être null"
        ))
            .orElseThrow(() ->
                new ObservationNotFoundException(dto.observation_id())
            );

        ObservationCritere observationCritere = repository.findById(Objects.requireNonNull(
            observation_critere_id,
            "L'identifiant de l'observation du critère ne peut pas être null"
        ))
            .orElseThrow(() ->
                new ObservationCritereNotFoundException(observation_critere_id)
            );
        observationCritere.setCritere(critere);
        observationCritere.setStatut(statut);
        observationCritere.setObservation(observation);
      
        ObservationCritere saved =
            repository.save(observationCritere);

        return mapper.toSearchDto(saved);
    }
}
