package com.java.planta_criteria.taxref;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.planta_criteria.taxref.dto.PlantSearchDto;

import java.util.List;

@RestController
public class TaxrefController {

    private final TaxrefService taxrefService;

    public TaxrefController(
        TaxrefService taxrefService
    ) {
        this.taxrefService = taxrefService;
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER')")
    public List<PlantSearchDto> search(
        @RequestParam(
            name = "q",
            defaultValue = ""
        ) String query
    ) {
        return taxrefService.search(query);
    }
}
