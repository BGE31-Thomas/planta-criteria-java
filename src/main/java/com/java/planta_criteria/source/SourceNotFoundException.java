package com.java.planta_criteria.source;

public class SourceNotFoundException extends RuntimeException {

    public SourceNotFoundException(Long id) {
        super("Source introuvable : " + id);
    }
}
