package com.java.planta_criteria.taxref;


public class TaxrefNotFoundException extends RuntimeException {

    public TaxrefNotFoundException(Integer id) {
        super("Taxon introuvable : " + id);
    }
}
