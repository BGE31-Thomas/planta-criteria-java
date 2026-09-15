package com.java.planta_criteria.taxref;


public class TaxrefNotFoundException extends RuntimeException {

    public TaxrefNotFoundException(Long id) {
        super("Taxon introuvable : " + id);
    }
}
