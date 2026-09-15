package com.java.planta_criteria.statut;

import com.java.planta_criteria.statut.dto.StatutDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/statuts")
@PreAuthorize("hasRole('USER')")
public class StatutController {

    private final StatutService statutService;

    public StatutController(StatutService statutService) {
        this.statutService = statutService;
    }

    @GetMapping
    public List<StatutDto> findAll() {
        return statutService.findAll();
    }

    @GetMapping("/{id}")
    public StatutDto findById(
        @PathVariable Long id
    ) {
        return statutService.findById(id);
    }
}
