package com.java.planta_criteria.observation;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.java.planta_criteria.observation_critere.ObservationCritere;

@Entity
@Table(name = "observation")
public class Observation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date_heure")
    private LocalDateTime dateHeure;

    @Column(length = 255, nullable = false)
    private String lieu;

    @OneToMany(
        mappedBy = "observation",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<ObservationCritere> observationsCritere =
        new ArrayList<>();

    public Long getId() {
        return id;
    }

    public LocalDateTime getDateHeure() {
        return dateHeure;
    }

    public void setDateHeure(LocalDateTime dateHeure) {
        this.dateHeure = dateHeure;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public List<ObservationCritere> getObservationsCritere() {
        return observationsCritere;
    }

    public void addObservationCritere(
        ObservationCritere observationCritere
    ) {
        observationsCritere.add(observationCritere);
        observationCritere.setObservation(this);
    }

    public void removeObservationCritere(
        ObservationCritere observationCritere
    ) {
        observationsCritere.remove(observationCritere);
        observationCritere.setObservation(null);
    }
}
