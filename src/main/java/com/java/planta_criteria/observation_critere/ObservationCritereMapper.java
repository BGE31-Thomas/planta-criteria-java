package com.java.planta_criteria.observation_critere;

@Component
public class ObservationCritereMapper {

    private final ImageMapper imageMapper;
    private final CritereMapper critereMapper;
    private final ObservationMapper observationMapper;
    private final StatutMapper statutMapper;

    public ObservationCritereMapper(ImageMapper imageMapper, CritereMapper critereMapper, ObservationMapper observationMapper, StatutMapper statutMapper) {
        this.imageMapper = imageMapper;
        this.critereMapper = critereMapper;
        this.observationMapper = observationMapper;
        this.statutMapper = statutMapper;
    }

    public ObservationCritereDto toSearchDto(ObservationCritere observationCritere) {
        return new ObservationCritereDto(
             
            observationCritere.getId(),
            observationMapper.toSearchDto(observationCritere.getObservation()),
            critereMapper.toSearchDto(observationCritere.getCritere()),
            statutMapper.toSearchDto(observationCritere.getStatut()),
            observationCritere.getImages()
                .stream()
                .map(imageMapper::toSearchDto)
                .toList()
           
        );
    }
}
