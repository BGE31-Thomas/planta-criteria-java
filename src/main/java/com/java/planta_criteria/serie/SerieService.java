package com.java.planta_criteria.serie;

import com.java.planta_criteria.observation.ObservationMapper;
import com.java.planta_criteria.observation.ObservationRepository;
import com.java.planta_criteria.observation.ObservationService;
import com.java.planta_criteria.observation.dto.ObservationDto;
import com.java.planta_criteria.serie.dto.SerieCreateDto;
import com.java.planta_criteria.serie.dto.SerieObservationCreateDto;
import com.java.planta_criteria.serie.dto.SerieObservationDto;
import com.java.planta_criteria.serie.dto.SerieSearchDto;
import com.java.planta_criteria.serie.dto.SerieSummaryDto;
import com.java.planta_criteria.user.UserNotFoundException;
import com.java.planta_criteria.user.UserRepository;
import com.java.planta_criteria.user.Users;
import com.java.planta_criteria.utils.SlugUtils;

import jakarta.persistence.EntityNotFoundException;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SerieService {

    private final ObservationRepository observationRepository;
    private final SerieRepository serieRepository;
    private final SerieMapper serieMapper;
    private final UserRepository userRepository;
    private final ObservationService observationService;


    public SerieService(
        SerieRepository serieRepository,
        UserRepository userRepository,
        SerieMapper serieMapper,
        ObservationService observationService,
        ObservationRepository observationRepository
        
    ) {
        this.serieRepository = serieRepository;
        this.userRepository = userRepository;
        this.serieMapper = serieMapper;
        this.observationService = observationService;
        this.observationRepository = observationRepository;
       
    }

    public SerieSearchDto findById(Integer id, String slug) {

        Serie serie = serieRepository.findById(Objects.requireNonNull(
            id,
            "L'identifiant de la série ne peut pas être null"
        ))
        .orElseThrow(() ->
            new SerieNotFoundException(id)
        );
        
        if(SlugUtils.toSlug(serie.getIntitule()).equals(slug) == false) {
            throw new IllegalArgumentException("Le slug fourni ne correspond pas à l'intitulé de la série");
        }

        return serieMapper.toSearchDto(serie);
    }

    @Transactional
    public SerieObservationDto createWithObservation(
        SerieObservationCreateDto dto,
        String userEmail
    ) {
        // 1. Récupération de l'utilisateur
        Users user = userRepository.findByEmail(
            Objects.requireNonNull(userEmail)
        ).orElseThrow(() ->
            new UserNotFoundException(userEmail)
        );

        // 2. Création de la série
        Serie serie = new Serie();

        serie.setIntitule(dto.getIntitule());
        serie.setLieu(dto.getLieu());
        serie.setDate(dto.getDate());
        serie.setUser(user);

        Serie savedSerie = serieRepository.save(serie);

        // 3. Création de l'observation et de ses critères
        ObservationDto observation = observationService.createWithCriteresSerie(
            savedSerie.getId(),
            dto.getPlanteId()
        );

        // 4. Retour des deux objets
        return new SerieObservationDto(
            serieMapper.toSearchDto(savedSerie),
            observation
        );
    }

    @Transactional
    public SerieSearchDto create(SerieCreateDto dto, String userEmail) {

        Serie serie = new Serie();

        serie.setIntitule(dto.getIntitule());
        serie.setLieu(dto.getLieu());
        serie.setDate(dto.getDate());
        System.out.println("User email: " + userEmail);
        Users user = userRepository.findByEmail(Objects.requireNonNull(
            userEmail,
            "L'identifiant de l'utilisateur ne peut pas être null"
        ))
        .orElseThrow(() ->
            new UserNotFoundException(userEmail)
        );

        serie.setUser(user);

        Serie saved =
            serieRepository.save(serie);

        return serieMapper.toSearchDto(saved);
    }

    @Transactional(readOnly = true)
    public List<SerieSearchDto> getAllByUser(String userEmail) {

        return serieRepository.findAllByUserEmail(userEmail)
            .stream()
            .map(serieMapper::toSearchDto)
            .toList();
    }

    @Transactional
    public ObservationDto addObservation(
        Integer serieId,
        Integer planteId,
        String userEmail
    ) {

        // Vérifier que la collection appartient à l'utilisateur
        serieRepository.findByIdAndUserEmail(
            serieId,
            userEmail
        ).orElseThrow(() ->
            new EntityNotFoundException("Collection introuvable")
        );

        // Vérifier si la plante est déjà présente
        boolean existe = observationRepository
            .existsBySerieIdAndPlanteCdNom(serieId, planteId);

        if (existe) {
            throw new IllegalStateException(
                "Cette plante est déjà présente dans cette collection."
            );
        }

        // Créer l'observation et ses critères
        ObservationDto observation =
            observationService.createWithCriteresSerie(serieId, planteId);

        return observation;
    }

    @Transactional
    public SerieSearchDto update(Integer serie_id,SerieSummaryDto dto,String slug) {

        Serie serie = serieRepository.findById(Objects.requireNonNull(
            serie_id,
            "L'identifiant de la série ne peut pas être null"
        ))
        .orElseThrow(() ->
            new SerieNotFoundException(serie_id)
        );;

        if(SlugUtils.toSlug(serie.getIntitule()).equals(slug) == false) {
            throw new IllegalArgumentException("Le slug fourni ne correspond pas à l'intitulé de la série");
        }



        serie.setIntitule(dto.intitule());
        serie.setLieu(dto.lieu());
        serie.setDate(dto.date());

        Serie saved =
            serieRepository.save(serie);

        return serieMapper.toSearchDto(saved);
    }
}

