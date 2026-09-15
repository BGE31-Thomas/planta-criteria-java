package com.java.planta_criteria.observation;

@Component
public class ObservationMapper {

    private final ObservationCritereMapper observationCritereMapper;

    public ObservationMapper(ObservationCritereMapper observationCritereMapper) {
        this.observationCritereMapper = observationCritereMapper;
    }

    public ObservationDto toSearchDto(Observation observation) {
        return new ObservationDto(
     
            observation.getId(),
            observation.getDate(),
            observation.getLieu(),
            observation.getObservationsCritere()
                .stream()
                .map(observationCritereMapper::toSearchDto)
                .toList()
           
        );
    }
}