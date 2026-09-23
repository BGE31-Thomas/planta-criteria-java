package com.java.planta_criteria.source;

import com.java.planta_criteria.source.dto.SourceDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sources")
public class SourceController {

    private final SourceService sourceService;

    public SourceController(SourceService sourceService) {
        this.sourceService = sourceService;
    }

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<SourceDto> findAll() {
        return sourceService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public SourceDto findById(
        @PathVariable Integer id
    ) {
        return sourceService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public SourceDto create(
        @RequestBody SourceDto dto
    ) {
        return sourceService.create(dto);
    }

    @PutMapping("/{id}")
    public SourceDto update(
        @PathVariable Integer id,
        @RequestBody SourceDto dto
    ) {
        return sourceService.update(id, dto);
    }
}
