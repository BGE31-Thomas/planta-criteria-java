package com.java.planta_criteria.commune.dto;

public class CommuneSearchDto {

    private Integer id;
    private String text;

    public CommuneSearchDto() {
    }

    public CommuneSearchDto(Integer id, String text) {
        this.id = id;
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setText(String text) {
        this.text = text;
    }
}