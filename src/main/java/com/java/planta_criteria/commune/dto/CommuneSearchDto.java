package com.java.planta_criteria.commune.dto;

public class CommuneSearchDto {

    private Long id;
    private String text;

    public CommuneSearchDto() {
    }

    public CommuneSearchDto(Long id, String text) {
        this.id = id;
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
}