package com.java.planta_criteria.critere;

import com.java.planta_criteria.critere.dto.CritereDto;
import com.java.planta_criteria.critere.dto.CritereCreateDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/criteres")
public class CritereController {

    private final CritereService critereService;

    public CritereController(CritereService critereService) {
        this.critereService = critereService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public CritereDto findById(
        @PathVariable Integer id
    ) {
        return critereService.findById(id);
    }

    @GetMapping("/plante/{planteId}")
    @PreAuthorize("hasRole('USER')")
    public List<CritereDto> findByPlante(
        @PathVariable Integer planteId
    ) {
        return critereService.findByPlante(planteId);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public CritereDto create(
        @RequestBody CritereCreateDto dto
    ) {
        return critereService.create(dto);
    }
}
