package com.java.planta_criteria.observation_critere;

import com.java.planta_criteria.observation_critere.dto.ObservationCritereDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/observations-criteres")
@PreAuthorize("hasRole('USER')")
public class ObservationCritereController {

    private final ObservationCritereService observationCritereService;

    public ObservationCritereController(
        ObservationCritereService observationCritereService
    ) {
        this.observationCritereService = observationCritereService;
    }

    @GetMapping
    public List<ObservationCritereDto> findAll() {
        return observationCritereService.findAll();
    }

    @GetMapping("/{id}")
    public ObservationCritereDto findById(
        @PathVariable Long id
    ) {
        return observationCritereService.findById(id);
    }
}
