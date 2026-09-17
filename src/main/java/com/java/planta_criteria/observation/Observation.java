package com.java.planta_criteria.observation;

import com.java.planta_criteria.observation_critere.ObservationCritere;
import com.java.planta_criteria.serie.Serie;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.java.planta_criteria.taxref.Taxref;

@Entity
@Table(name = "observation")
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(
        fetch = FetchType.LAZY,
        cascade = CascadeType.PERSIST,
        optional = false
    )
    @JoinColumn(name = "serie_id", nullable = false)
    private Serie serie;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "plante_id", nullable = false)
    private Taxref plante;

    @OneToMany(
        mappedBy = "observation",
        cascade = CascadeType.PERSIST,
        orphanRemoval = true
    )
    private List<ObservationCritere> observationsCritere = new ArrayList<>();


    public Integer getId() {
        return id;
    }

    public Serie getSerie() {
        return serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    public List<ObservationCritere> getObservationsCritere() {
        return observationsCritere;
    }

    public void addObservationsCritere(ObservationCritere oc) {
        if (!observationsCritere.contains(oc)) {
            observationsCritere.add(oc);
            oc.setObservation(this);
        }
    }

    public void removeObservationsCritere(ObservationCritere oc) {
        if (observationsCritere.remove(oc)) {
            if (oc.getObservation() == this) {
                oc.setObservation(null);
            }
        }
    }

    public Taxref getPlante() {
        return plante;
    }

    public void setPlante(Taxref plante) {
        this.plante = plante;
    }
}