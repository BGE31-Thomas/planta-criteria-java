package com.java.planta_criteria.commune;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.planta_criteria.commune.dto.CommuneSearchDto;

import java.util.List;

@RestController
@RequestMapping("/api/communes")
public class CommuneController {

    private final CommuneService communeService;

    public CommuneController(
        CommuneService communeService
    ) {
        this.communeService = communeService;
    }

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
