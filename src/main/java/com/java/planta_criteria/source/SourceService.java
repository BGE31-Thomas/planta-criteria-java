package com.java.planta_criteria.source;

import com.java.planta_criteria.source.dto.SourceDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

    public SourceDto findById(Long id) {

        Source source = sourceRepository.findById(id)
            .orElseThrow(() ->
                new SourceNotFoundException(id)
            );

        return sourceMapper.toSearchDto(source);
    }
}
