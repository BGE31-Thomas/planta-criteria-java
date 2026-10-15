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

    @GetMapping("/{id}/{slug}")
    @PreAuthorize("hasRole('USER')")
    public SourceDto findById(
        @PathVariable Integer id,
        @PathVariable String slug
    ) {
        return sourceService.findById(id, slug);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public SourceDto create(
        @RequestBody SourceDto dto
    ) {
        return sourceService.create(dto);
    }

    @PutMapping("/{id}/{slug}")
    @PreAuthorize("hasRole('USER')")
    public SourceDto update(
        @PathVariable Integer id,
        @PathVariable String slug,
        @RequestBody SourceDto dto
    ) {
        return sourceService.update(id, dto, slug);
    }
}
