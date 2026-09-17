package com.java.planta_criteria.observation_critere;

import com.java.planta_criteria.critere.Critere;
import com.java.planta_criteria.image.Image;
import com.java.planta_criteria.observation.Observation;
import com.java.planta_criteria.statut.Statut;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "observation_critere")
public class ObservationCritere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "observation_id", nullable = false)
    private Observation observation;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "critere_id", nullable = false)
    private Critere critere;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "statut_id", nullable = false)
    private Statut statut;

    @OneToMany(
        mappedBy = "observationCritere",
        cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
        orphanRemoval = true
    )
    private List<Image> images = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public Observation getObservation() {
        return observation;
    }

    public void setObservation(Observation observation) {
        this.observation = observation;
    }

    public Critere getCritere() {
        return critere;
    }

    public void setCritere(Critere critere) {
        this.critere = critere;
    }

    public Statut getStatut() {
        return statut;
    }

    public void setStatut(Statut statut) {
        this.statut = statut;
    }

    public List<Image> getImages() {
        return images;
    }

    public void addImage(Image image) {
        if (!images.contains(image)) {
            images.add(image);
            image.setObservationCritere(this);
        }
    }

    public void removeImage(Image image) {
        if (images.remove(image)) {
            if (image.getObservationCritere() == this) {
                image.setObservationCritere(null);
            }
        }
    }
}