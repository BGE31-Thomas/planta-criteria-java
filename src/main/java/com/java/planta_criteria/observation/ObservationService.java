package com.java.planta_criteria.observation;

import com.java.planta_criteria.critere.Critere;
import com.java.planta_criteria.critere.CritereRepository;
import com.java.planta_criteria.observation.dto.ObservationDto;
import com.java.planta_criteria.observation.dto.ObservationUpdateDto;
import com.java.planta_criteria.observation_critere.ObservationCritere;
import com.java.planta_criteria.observation_critere.ObservationCritereRepository;
import com.java.planta_criteria.taxref.Taxref;
import com.java.planta_criteria.taxref.TaxrefRepository;

import jakarta.persistence.EntityNotFoundException;

import com.java.planta_criteria.taxref.TaxrefNotFoundException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.java.planta_criteria.serie.Serie;
import com.java.planta_criteria.serie.SerieRepository;
import com.java.planta_criteria.statut.Statut;
import com.java.planta_criteria.statut.StatutRepository;
import com.java.planta_criteria.serie.SerieNotFoundException;

import java.util.List;
import java.util.Objects;

@Service
public class ObservationService {

    private final ObservationRepository observationRepository;
    private final ObservationMapper observationMapper;
    private final TaxrefRepository taxrefRepository;
    private final SerieRepository serieRepository;
    private final CritereRepository critereRepository;
    private final ObservationCritereRepository observationCritereRepository;
    private final StatutRepository statutRepository;

    public ObservationService(
        ObservationRepository observationRepository,
        CritereRepository critereRepository,
        ObservationCritereRepository observationCritereRepository,
        ObservationMapper observationMapper,
        SerieRepository serieRepository,
        TaxrefRepository taxrefRepository,
        StatutRepository statutRepository
        
    ) {
        this.observationRepository = observationRepository;
        this.critereRepository = critereRepository;
        this.observationCritereRepository = observationCritereRepository;
        this.observationMapper = observationMapper;
        this.serieRepository = serieRepository;
        this.taxrefRepository = taxrefRepository;
        this.statutRepository = statutRepository;
       
    }

    @Transactional
    public ObservationDto createWithCriteresSerie(
        Integer serieId,
        Integer planteId
    ) {
        System.out.println("Creating observation with serieId: " + serieId + " and planteId: " + planteId);
        Serie serie = serieRepository.findById(
            Objects.requireNonNull(serieId)
        ).orElseThrow(() ->
            new SerieNotFoundException(serieId)
        );

        Taxref plante = taxrefRepository.findById(
            Objects.requireNonNull(planteId)
        ).orElseThrow(() ->
            new TaxrefNotFoundException(planteId)
        );

        // Création de l'observation
        Observation observation = new Observation();

        observation.setPlante(plante);
        observation.setSerie(serie);

        Observation savedObservation =
            observationRepository.save(observation);

        // Récupération des critères de la plante
        List<Critere> criteres =
            critereRepository.findByPlante(plante);

        // Récupération du statut initial (une seule fois)
        Statut statutInitial = statutRepository.findByLibelle("Non vérifié")
            .orElseThrow(() ->
                new EntityNotFoundException("Statut initial introuvable")
            );

        // Création des observations de critères
        List<ObservationCritere> observationsCriteres =
            criteres.stream().map(critere -> {

                ObservationCritere oc = new ObservationCritere();

                oc.setObservation(savedObservation);
                oc.setCritere(critere);
                oc.setStatut(statutInitial);

                return oc;

            }).toList();

        observationCritereRepository.saveAll(Objects.requireNonNull(observationsCriteres));

        return observationMapper.toSearchDto(savedObservation);
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