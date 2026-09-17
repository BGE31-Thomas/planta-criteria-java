package com.java.planta_criteria.observation;

import com.java.planta_criteria.critere.Critere;
import com.java.planta_criteria.critere.CritereRepository;
import com.java.planta_criteria.observation.dto.ObservationCreateDto;
import com.java.planta_criteria.observation.dto.ObservationDto;
import com.java.planta_criteria.observation_critere.ObservationCritere;
import com.java.planta_criteria.statut.Statut;
import com.java.planta_criteria.statut.StatutRepository;
import com.java.planta_criteria.taxref.Taxref;
import com.java.planta_criteria.taxref.TaxrefRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.planta_criteria.serie.Serie;
import com.java.planta_criteria.serie.SerieRepository;

import java.util.List;

@Service
public class ObservationService {

    private static final String STATUT_NON_VERIFIE = "Non vérifié";

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

        Observation observation = observationRepository.findById(id)
            .orElseThrow(() ->
                new ObservationNotFoundException(id)
            );

        return observationMapper.toSearchDto(observation);
    }

    @Transactional
    public ObservationDto create(ObservationCreateDto dto) {

        Taxref plante = taxrefRepository.findById(dto.getPlanteId())
            .orElseThrow(() ->
                new IllegalArgumentException(
                    "Plante introuvable : " + dto.getPlanteId()
                )
            );

        Serie serie = serieRepository.findById(dto.getSerieId())
            .orElseThrow(() ->
                new IllegalArgumentException(
                    "Série introuvable : " + dto.getSerieId()
                )
            );

        Observation observation = new Observation();

        observation.setPlante(plante);
        observation.setSerie(serie);
      
        Observation saved =
            observationRepository.save(observation);

        return observationMapper.toSearchDto(saved);
    }
}