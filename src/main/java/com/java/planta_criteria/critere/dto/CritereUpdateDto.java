package com.java.planta_criteria.critere.dto;

public record CritereUpdateDto(
    Integer id,
    String organe,
    String description,
    Integer source_id,
    Integer plante_id
){

}
