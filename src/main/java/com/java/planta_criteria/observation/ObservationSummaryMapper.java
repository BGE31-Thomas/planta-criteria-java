package com.java.planta_criteria.observation;

import com.java.planta_criteria.observation.dto.ObservationSummaryDto;
import org.springframework.stereotype.Component;

import com.java.planta_criteria.observation.Observation;

@Component
public class ObservationSummaryMapper {
    public ObservationSummaryDto toSummaryDto(
        Observation observation
    ) {

        return new ObservationSummaryDto(
            observation.getId()
        );
    }
}
