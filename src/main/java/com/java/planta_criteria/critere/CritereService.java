package com.java.planta_criteria.critere;

import com.java.planta_criteria.critere.dto.CritereCreateDto;
import com.java.planta_criteria.critere.dto.CritereDto;
import com.java.planta_criteria.source.SourceRepository;
import com.java.planta_criteria.taxref.Taxref;
import com.java.planta_criteria.taxref.TaxrefRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public CritereDto findById(Integer id) {

        Critere critere = critereRepository.findById(id)
            .orElseThrow(() ->
                new CritereNotFoundException(id)
            );

        return critereMapper.toSearchDto(critere);
    }

    public List<CritereDto> findByPlante(Integer planteId) {

        Taxref plante = taxrefRepository.findById(planteId)
            .orElseThrow(() ->
                new IllegalArgumentException(
                    "Plante introuvable : " + planteId
                )
            );

        return critereRepository
            .findByPlante(plante)
            .stream()
            .map(critereMapper::toSearchDto)
            .toList();
    }

    @Transactional
    public CritereDto create(CritereCreateDto dto) {

        Taxref plante = taxrefRepository.findById(dto.getPlanteId())
            .orElseThrow(() ->
                new IllegalArgumentException(
                    "Plante introuvable : " + dto.getPlanteId()
                )
            );

        Source source = sourceRepository.findById(dto.getSource_id())
            .orElseThrow(() ->
                new IllegalArgumentException(
                    "Source introuvable : " + dto.getSource_id()
                )
            );

        Critere critere = new Critere();

        critere.setPlante(plante);
        critere.setSource(source);
        critere.setOrgane(dto.getOrgane());
        critere.setDescription(dto.getDescription());

        Critere saved =
            critereRepository.save(observation);

        return critereMapper.toSearchDto(saved);
    }
}
