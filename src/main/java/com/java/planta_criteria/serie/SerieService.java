package com.java.planta_criteria.serie;

import com.java.planta_criteria.serie.dto.SerieCreateDto;
import com.java.planta_criteria.serie.dto.SerieSearchDto;
import com.java.planta_criteria.serie.dto.SerieSummaryDto;
import com.java.planta_criteria.utils.SlugUtils;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class SerieService {

    private final SerieRepository serieRepository;
    private final SerieMapper serieMapper;

    public SerieService(
        SerieRepository serieRepository,
        SerieMapper serieMapper
    ) {
        this.serieRepository = serieRepository;
        this.serieMapper = serieMapper;
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
    public SerieSearchDto create(SerieCreateDto dto) {

        Serie serie = new Serie();

        serie.setIntitule(dto.getIntitule());
        serie.setLieu(dto.getLieu());
        serie.setDate(dto.getDate());

        Serie saved =
            serieRepository.save(serie);

        return serieMapper.toSearchDto(saved);
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

