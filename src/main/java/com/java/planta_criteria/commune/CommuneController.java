package com.java.planta_criteria.commune;

import com.java.planta_criteria.commune.dto.CommuneSearchDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/communes")
public class CommuneController {

    private final CommuneService communeService;

    public CommuneController(CommuneService communeService) {
        this.communeService = communeService;
    }

    /**
     * Symfony :
     * GET /api/communes?q=...
     */
    @GetMapping
    public List<CommuneSearchDto> search(
        @RequestParam(
            name = "q",
            defaultValue = ""
        ) String query
    ) {
        return communeService.search(query);
    }
}