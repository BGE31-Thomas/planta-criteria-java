package com.java.planta_criteria.observation.dto;


public class ObservationCreateDto{
    private Integer id;
    private Integer serieId;
    private Integer planteId;
   

    public ObservationCreateDto() {
    }

    public ObservationCreateDto(Integer id, Integer serieId, Integer planteId) {
        this.id = id;
        this.serieId = serieId;
        this.planteId = planteId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
