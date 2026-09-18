package com.java.planta_criteria.taxref;

import com.java.planta_criteria.taxref.Taxref;
import com.java.planta_criteria.taxref.dto.TaxrefSummaryDto;
import org.springframework.stereotype.Component;

@Component
public class TaxrefSummaryMapper {
    public TaxrefSummaryDto toTaxrefSummaryDto(Taxref taxref) {
        return new TaxrefSummaryDto(
                taxref.getCdNom(),
                taxref.getNom(),
                taxref.getLbNom()
        );
    }
}
