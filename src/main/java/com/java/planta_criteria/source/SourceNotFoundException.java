package com.java.planta_criteria.source;

public class SourceNotFoundException extends RuntimeException {

    public SourceNotFoundException(Integer id) {
        super("Source introuvable : " + id);
    }
}
