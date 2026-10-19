package com.java.planta_criteria.taxref;

import com.java.planta_criteria.taxref.dto.PlantDto;
import com.java.planta_criteria.taxref.dto.PlantSearchDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/plants")
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
        List<PlantSearchDto> results = taxrefService.search(query);

        System.out.println(">>> SEARCH : " + query);
        System.out.println(">>> RESULTS : " + results.size());
        System.out.println(">>> FIRST : " +
        (results.isEmpty() ? "none" : results.get(0)));

        return results;
    }

    /**
     * Détail d'une plante.
     
     */
    @GetMapping("/{id}/{slug}")
    @PreAuthorize("hasRole('USER')")
    public PlantDto findById(
        @PathVariable Integer id,
        @PathVariable String slug
    ) {
        System.out.println(">>> FIND BY ID : " + id + " / " + slug);
        return taxrefService.findByIdOrValidTaxon(id, slug);
    }
}