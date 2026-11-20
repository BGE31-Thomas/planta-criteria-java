package com.java.planta_criteria.observation;

import com.java.planta_criteria.observation.dto.ObservationCreateDto;
import com.java.planta_criteria.observation.dto.ObservationDto;
import com.java.planta_criteria.observation.dto.ObservationUpdateDto;
import com.java.planta_criteria.serie.SerieService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/observations")
@PreAuthorize("hasRole('USER')")
public class ObservationController {

    private final ObservationService observationService;
    private final SerieService serieService;

    public ObservationController(
        ObservationService observationService,
        SerieService serieService
    ) {
        this.observationService = observationService;
        this.serieService = serieService;

    }

    @GetMapping
    public List<ObservationDto> findAll() {
        return observationService.findAll();
    }

    @GetMapping("/{id}")
    public ObservationDto findById(
        @PathVariable Integer id
    ) {
        return observationService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ObservationDto addObservation(
       
        @RequestBody ObservationCreateDto dto,
        Authentication authentication
    ) {
        return serieService.addObservation(
            dto.getSerieId(),
            dto.getPlanteId(),
            authentication.getName()
        );
    }

    @PutMapping("/{id}")
    public ObservationDto update(
        @PathVariable Integer id,
        @RequestBody ObservationUpdateDto dto
    ) {
        return observationService.update(id, dto);
    }
}