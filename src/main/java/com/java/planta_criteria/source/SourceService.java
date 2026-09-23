package com.java.planta_criteria.source;

import com.java.planta_criteria.source.dto.SourceDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class SourceService {

    private final SourceRepository sourceRepository;
    private final SourceMapper sourceMapper;

    public SourceService(
        SourceRepository sourceRepository,
        SourceMapper sourceMapper
    ) {
        this.sourceRepository = sourceRepository;
        this.sourceMapper = sourceMapper;
    }

    public List<SourceDto> findAll() {

        return sourceRepository.findAll()
            .stream()
            .map(sourceMapper::toSearchDto)
            .toList();
    }

    public SourceDto findById(Integer id) {

        Source source = sourceRepository.findById(Objects.requireNonNull(
            id,
            "L'identifiant de la source ne peut pas être null"
        ))
            .orElseThrow(() ->
                new SourceNotFoundException(id)
            );

        return sourceMapper.toSearchDto(source);
    }

    @Transactional
    public SourceDto create(SourceDto dto) {

        Source source = new Source();

        source.setAuteur(dto.getAuteur());
        source.setAnnee(dto.getAnnee());
        source.setTitre(dto.getTitre());

        Source saved =
            sourceRepository.save(source);

        return sourceMapper.toSearchDto(saved);
    }

    @Transactional
    public SourceDto update(Integer source_id, SourceDto dto) {

        Source source = sourceRepository.findById(Objects.requireNonNull(
            source_id,
            "L'identifiant de la série ne peut pas être null"
        ))
            .orElseThrow(() ->
                new SourceNotFoundException(source_id)
            );;

        source.setTitre(dto.getTitre());
        source.setAuteur(dto.getAuteur());
        source.setTitre(dto.getTitre());

        Source saved =
            sourceRepository.save(source);

        return sourceMapper.toSearchDto(saved);
    }
}
