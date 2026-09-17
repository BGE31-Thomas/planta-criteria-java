package com.java.planta_criteria.serie;

import com.java.planta_criteria.observation.Observation;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "serie")
public class Serie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 255, nullable = false)
    private String intitule;

    @Column(length = 255, nullable = false)
    private String lieu;

    @Column(nullable = false)
    private LocalDate date;

    @OneToMany(
        mappedBy = "serie",
        cascade = CascadeType.PERSIST,
        orphanRemoval = true
    )
    private List<Observation> observations = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Observation> getObservations() {
        return observations;
    }

    public void addObservation(Observation observation) {
        if (!observations.contains(observation)) {
            observations.add(observation);
            observation.setSerie(this);
        }
    }

    public void removeObservation(Observation observation) {
        if (observations.remove(observation)) {
            if (observation.getSerie() == this) {
                observation.setSerie(null);
            }
        }
    }

}
