package com.java.planta_criteria.critere;

import com.java.planta_criteria.critere.dto.CritereCreateDto;
import com.java.planta_criteria.critere.dto.CritereDto;
import com.java.planta_criteria.critere.dto.CritereUpdateDto;

import com.java.planta_criteria.source.SourceRepository;
import com.java.planta_criteria.source.SourceNotFoundException;

import com.java.planta_criteria.taxref.Taxref;
import com.java.planta_criteria.taxref.TaxrefRepository;
import com.java.planta_criteria.taxref.TaxrefNotFoundException;

import io.micrometer.common.lang.NonNull;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.java.planta_criteria.source.Source;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class CritereService {

    private final CritereRepository critereRepository;
    private final TaxrefRepository taxrefRepository;
    private final SourceRepository sourceRepository;
    private final CritereMapper critereMapper;

    public CritereService(
        CritereRepository critereRepository,
        TaxrefRepository taxrefRepository,
        CritereMapper critereMapper,
        SourceRepository sourceRepository
    ) {
        this.critereRepository = critereRepository;
        this.taxrefRepository = taxrefRepository;
        this.critereMapper = critereMapper;
        this.sourceRepository = sourceRepository;
    }

    public CritereDto findById(@NonNull Integer id) {

        Critere critere = critereRepository.findById(Objects.requireNonNull(id, "L'identifiant du critère ne peut pas être null"))
            .orElseThrow(() ->
                new CritereNotFoundException(id)
            );

        return critereMapper.toSearchDto(critere);
    }

    public List<CritereDto> findByPlante(@NonNull Integer planteId) {

        Taxref plante = taxrefRepository.findById(
            Objects.requireNonNull(planteId, "L'identifiant de la plante ne peut pas être null")
        )
            .orElseThrow(() ->
                new TaxrefNotFoundException(planteId)
            );

        return critereRepository
            .findByPlante(plante)
            .stream()
            .map(critereMapper::toSearchDto)
            .toList();
    }

    @Transactional
    public CritereDto create(CritereCreateDto dto) {

        Integer planteId = Objects.requireNonNull(
            dto.getPlante_id(),
            "L'identifiant de la plante ne peut pas être null"
        );

        Taxref plante = taxrefRepository.findById(planteId)
            .orElseThrow(() ->
                new TaxrefNotFoundException(planteId)
            );

        Source source = sourceRepository.findById(Objects.requireNonNull(
            dto.getSource_id(),
            "L'identifiant de la source ne peut pas être null"
        ))
            .orElseThrow(() ->
                new SourceNotFoundException(dto.getSource_id())
            );

        Critere critere = new Critere();

        critere.setPlante(plante);
        critere.setSource(source);
        critere.setOrgane(dto.getOrgane());
        critere.setDescription(dto.getDescription());

        Critere saved =
            critereRepository.save(critere);

        return critereMapper.toSearchDto(saved);
    }

    @Transactional
    public CritereDto update(Integer critere_id, CritereUpdateDto dto) {

        Integer planteId = Objects.requireNonNull(
            dto.plante_id(),
            "L'identifiant de la plante ne peut pas être null"
        );

        Taxref plante = taxrefRepository.findById(planteId)
            .orElseThrow(() ->
                new TaxrefNotFoundException(planteId)
            );

        Source source = sourceRepository.findById(Objects.requireNonNull(
            dto.source_id(),
            "L'identifiant de la source ne peut pas être null"
        ))
            .orElseThrow(() ->
                new SourceNotFoundException(dto.source_id())
            );

        Critere critere = critereRepository.findById(Objects.requireNonNull(
            critere_id,
            "L'identifiant de la source ne peut pas être null"
        ))
            .orElseThrow(() ->
                new CritereNotFoundException(critere_id)
            );

        critere.setPlante(plante);
        critere.setSource(source);
        critere.setOrgane(dto.organe());
        critere.setDescription(dto.description());

        Critere saved =
            critereRepository.save(critere);

        return critereMapper.toSearchDto(saved);
    }
}
