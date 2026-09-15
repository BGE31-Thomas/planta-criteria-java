package com.java.planta_criteria.commune;

import jakarta.persistence.*;

@Entity
@Table(
    name = "commune",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uniq_nom_cp",
            columnNames = {"nom", "code_postal"}
        )
    }
)
public class Commune{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String nom;

    @Column(length = 6, nullable = false)
    private String code_postal;

    public Long getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }


}

