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

import java.util.List;

@Service
public class ObservationService {

    private static final String STATUT_NON_VERIFIE = "Non vérifié";

    private final ObservationRepository observationRepository;
    private final ObservationMapper observationMapper;
    private final TaxrefRepository taxrefRepository;
    private final CritereRepository critereRepository;
    private final StatutRepository statutRepository;

    public ObservationService(
        ObservationRepository observationRepository,
        ObservationMapper observationMapper,
        TaxrefRepository taxrefRepository,
        CritereRepository critereRepository,
        StatutRepository statutRepository
    ) {
        this.observationRepository = observationRepository;
        this.observationMapper = observationMapper;
        this.taxrefRepository = taxrefRepository;
        this.critereRepository = critereRepository;
        this.statutRepository = statutRepository;
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

        Statut statutNonVerifie =
            statutRepository.findByLibelle(STATUT_NON_VERIFIE)
                .orElseThrow(() ->
                    new IllegalStateException(
                        "Le statut '" + STATUT_NON_VERIFIE
                            + "' n'existe pas en base de données"
                    )
                );

        Observation observation = new Observation();

        observation.setDateHeure(dto.getDateHeure());
        observation.setLieu(dto.getLieu());

        List<Critere> criteres =
            critereRepository.findByPlante(plante);

        for (Critere critere : criteres) {

            ObservationCritere observationCritere =
                new ObservationCritere();

            observationCritere.setCritere(critere);
            observationCritere.setStatut(statutNonVerifie);

            observation.addObservationCritere(
                observationCritere
            );
        }

        Observation saved =
            observationRepository.save(observation);

        return observationMapper.toSearchDto(saved);
    }
}