package com.java.planta_criteria.serie;

import com.java.planta_criteria.serie.dto.SerieSearchDto;
import com.java.planta_criteria.serie.dto.SerieCreateDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/series")
public class SerieController {

    private final SerieService serieService;

    public SerieController(SerieService serieService) {
        this.serieService = serieService;
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public SerieSearchDto findById(
        @PathVariable Integer id
    ) {
        return serieService.findById(id);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public SerieSearchDto create(
        @RequestBody SerieCreateDto dto
    ) {
        return serieService.create(dto);
    }
}
