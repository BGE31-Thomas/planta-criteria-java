package com.java.planta_criteria.taxref;

import com.java.planta_criteria.taxref.dto.PlantDto;
import com.java.planta_criteria.taxref.dto.PlantSearchDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaxrefController {

    private final TaxrefService taxrefService;

    public TaxrefController(TaxrefService taxrefService) {
        this.taxrefService = taxrefService;
    }

    /**
     * Recherche utilisée par l'autocomplete Angular.
     *
     * Symfony :
     * GET /search?q=...
     */
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

    /**
     * Détail d'une plante.
     *
     * Symfony :
     * GET /plant/{id}
     */
    @GetMapping("/plant/{id}")
    @PreAuthorize("hasRole('USER')")
    public PlantDto findById(
        @PathVariable Integer id
    ) {
        return taxrefService.findByIdOrValidTaxon(id);
    }
}