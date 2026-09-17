package com.java.planta_criteria.observation.dto;

public class ObservationCreateDto{
    private Integer serieId;
    private Integer planteId;

    public ObservationCreateDto() {
    }

    public ObservationCreateDto(Integer serieId, Integer planteId) {
        this.serieId = serieId;
        this.planteId = planteId;
    }

    public Integer getSerieId() {
        return serieId;
    }

    public void setSerieId(Integer serieId) {
        this.serieId = serieId;
    }

    public Integer getPlanteId() {
        return planteId;
    }

    public void setPlanteId(Integer planteId) {
        this.planteId = planteId;
    }
}
