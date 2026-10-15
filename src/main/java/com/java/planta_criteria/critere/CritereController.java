package com.java.planta_criteria.critere;

import com.java.planta_criteria.critere.dto.CritereDto;
import com.java.planta_criteria.critere.dto.CritereUpdateDto;
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

    @GetMapping("/plante/{planteId}/{slug}")
    @PreAuthorize("hasRole('USER')")
    public List<CritereDto> findByPlante(
        @PathVariable Integer planteId,
        @PathVariable String slug
    ) {
        return critereService.findByPlante(planteId, slug);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public CritereDto create(
        @RequestBody CritereCreateDto dto
    ) {
        return critereService.create(dto);
    }

    @PutMapping("/{id}/{slug}")
    public CritereDto update(
        @PathVariable Integer id,
        @PathVariable String slug,
        @RequestBody CritereUpdateDto dto
    ) {
        return critereService.update(id, dto, slug);
    }
}
