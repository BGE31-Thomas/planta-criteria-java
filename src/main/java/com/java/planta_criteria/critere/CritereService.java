package com.java.planta_criteria.critere;

import com.java.planta_criteria.critere.dto.CritereDto;
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
    private final CritereMapper critereMapper;

    public CritereService(
        CritereRepository critereRepository,
        TaxrefRepository taxrefRepository,
        CritereMapper critereMapper
    ) {
        this.critereRepository = critereRepository;
        this.taxrefRepository = taxrefRepository;
        this.critereMapper = critereMapper;
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
}
