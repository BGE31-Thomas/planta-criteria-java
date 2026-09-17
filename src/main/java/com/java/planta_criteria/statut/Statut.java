package com.java.planta_criteria.statut;

import java.util.ArrayList;
import java.util.List;

import com.java.planta_criteria.observation_critere.ObservationCritere;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "statut")
public class Statut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false)
    private String libelle;

    @OneToMany(
        mappedBy = "statut",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ObservationCritere> observationsCriteres = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public List<ObservationCritere> getObservationsCriteres() {
        return observationsCriteres;
    }

    public void addObservationCritere(
        ObservationCritere observationCritere
    ) {
        observationsCriteres.add(observationCritere);
    }

    public void removeObservationCritere(ObservationCritere observationCritere) {
        observationsCriteres.remove(observationCritere);
        observationCritere.setStatut(null);
    }


}
