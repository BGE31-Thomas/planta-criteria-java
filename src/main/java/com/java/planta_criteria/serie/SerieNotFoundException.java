package com.java.planta_criteria.serie;

public class SerieNotFoundException extends RuntimeException {

    public SerieNotFoundException(Integer id) {
        super("Taxon introuvable : " + id);
    }
}
