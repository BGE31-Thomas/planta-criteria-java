package com.java.planta_criteria.observation;

import com.java.planta_criteria.observation.dto.ObservationCreateDto;
import com.java.planta_criteria.observation.dto.ObservationDto;
import com.java.planta_criteria.observation.dto.ObservationUpdateDto;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/observations")
@PreAuthorize("hasRole('USER')")
public class ObservationController {

    private final ObservationService observationService;

    public ObservationController(
        ObservationService observationService
    ) {
        this.observationService = observationService;
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
    public ObservationDto create(
        @RequestBody ObservationCreateDto dto
    ) {
        return observationService.create(dto);
    }

    @PutMapping("/{id}")
    public ObservationDto update(
        @PathVariable Integer id,
        @RequestBody ObservationUpdateDto dto
    ) {
        return observationService.update(id, dto);
    }
}