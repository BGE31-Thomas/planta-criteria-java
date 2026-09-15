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
    @PreAuthorize("hasRole('ADMIN')")
    public List<SourceDto> findAll() {
        return sourceService.findAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public SourceDto findById(
        @PathVariable Long id
    ) {
        return sourceService.findById(id);
    }
}
