package com.java.planta_criteria.serie;

import com.java.planta_criteria.serie.dto.SerieSearchDto;
import com.java.planta_criteria.serie.dto.SerieSummaryDto;
import com.java.planta_criteria.serie.dto.SerieCreateDto;
import com.java.planta_criteria.serie.dto.SerieObservationCreateDto;
import com.java.planta_criteria.serie.dto.SerieObservationDto;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/series")
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping("/{id}/{slug}")
    @PreAuthorize("hasRole('USER')")
    public SerieSearchDto findById(
        @PathVariable Integer id,
        @PathVariable String slug
    ) {
        return serieService.findById(id, slug);
    }

    @PostMapping("/observation")
    @PreAuthorize("hasRole('USER')")
    public SerieObservationDto create(
        @RequestBody SerieObservationCreateDto dto,
        Authentication authentication
    ) {
        String email = authentication.getName();

        return serieService.createWithObservation(dto, email);
    }

    @PutMapping("/{id}/{slug}")
    public SerieSearchDto update(
        @PathVariable Integer id,
        @PathVariable String slug,
        @RequestBody SerieSummaryDto dto
    ) {
        return serieService.update(id, dto, slug);
    }
}
