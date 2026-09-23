package com.java.planta_criteria.observation;

import com.java.planta_criteria.observation.dto.ObservationCreateDto;
import com.java.planta_criteria.observation.dto.ObservationDto;
import com.java.planta_criteria.observation.dto.ObservationUpdateDto;

import com.java.planta_criteria.taxref.Taxref;
import com.java.planta_criteria.taxref.TaxrefRepository;
import com.java.planta_criteria.taxref.TaxrefNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.planta_criteria.serie.Serie;
import com.java.planta_criteria.serie.SerieRepository;
import com.java.planta_criteria.serie.SerieNotFoundException;

import java.util.List;
import java.util.Objects;

@Service
public class ObservationService {

    private final ObservationRepository observationRepository;
    private final ObservationMapper observationMapper;
    private final TaxrefRepository taxrefRepository;
    private final SerieRepository serieRepository;

    public ObservationService(
        ObservationRepository observationRepository,
        ObservationMapper observationMapper,
        TaxrefRepository taxrefRepository,
        SerieRepository serieRepository
    ) {
        this.observationRepository = observationRepository;
        this.observationMapper = observationMapper;
        this.taxrefRepository = taxrefRepository;
        this.serieRepository = serieRepository;
    }

    @Transactional(readOnly = true)
    public List<ObservationDto> findAll() {

        return observationRepository.findAll()
            .stream()
            .map(observationMapper::toSearchDto)
            .toList();
    }

    @Transactional(readOnly = true)
    public ObservationDto findById(Integer id) {

        Observation observation = observationRepository.findById(Objects.requireNonNull(
            id,
            "L'identifiant de l'observation ne peut pas être null"
        ))
            .orElseThrow(() ->
                new ObservationNotFoundException(id)
            );

        return observationMapper.toSearchDto(observation);
    }

    @Transactional
    public ObservationDto create(ObservationCreateDto dto) {

        Taxref plante = taxrefRepository.findById(Objects.requireNonNull(
            dto.getPlanteId(),
            "L'identifiant de la plante ne peut pas être null"
        ))
            .orElseThrow(() ->
                new TaxrefNotFoundException(dto.getPlanteId())
            );

        Serie serie = serieRepository.findById(Objects.requireNonNull(
            dto.getSerieId(),
            "L'identifiant de la série ne peut pas être null"
        ))
            .orElseThrow(() ->
                new SerieNotFoundException(dto.getSerieId())
            );

        Observation observation = new Observation();

        observation.setPlante(plante);
        observation.setSerie(serie);
      
        Observation saved =
            observationRepository.save(observation);

        return observationMapper.toSearchDto(saved);
    }

    @Transactional
    public ObservationDto update(Integer observation_id, ObservationUpdateDto dto) {

        Taxref plante = taxrefRepository.findById(Objects.requireNonNull(
            dto.plante_id(),
            "L'identifiant de la plante ne peut pas être null"
        ))
            .orElseThrow(() ->
                new TaxrefNotFoundException(dto.plante_id())
            );

        Serie serie = serieRepository.findById(Objects.requireNonNull(
            dto.serie_id(),
            "L'identifiant de la série ne peut pas être null"
        ))
            .orElseThrow(() ->
                new SerieNotFoundException(dto.serie_id())
            );

        Observation observation = observationRepository.findById(Objects.requireNonNull(
            observation_id,
            "L'identifiant de l'observation ne peut pas être null"
        ))
            .orElseThrow(() ->
                new ObservationNotFoundException(observation_id)
            );

        observation.setPlante(plante);
        observation.setSerie(serie);
      
        Observation saved =
            observationRepository.save(observation);

        return observationMapper.toSearchDto(saved);
    }
}