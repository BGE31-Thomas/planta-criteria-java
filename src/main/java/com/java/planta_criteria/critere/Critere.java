package com.java.planta_criteria.critere;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

import com.java.planta_criteria.image.Image;
import com.java.planta_criteria.source.Source;
import com.java.planta_criteria.taxref.Taxref;
import com.java.planta_criteria.user.User;

@Entity
@Table(name = "critere")
public class Critere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255, nullable = false)
    private String organe;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "plante_id",
        referencedColumnName = "cd_nom",
        nullable = false
    )
    private Taxref plante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id")
    private Source source;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
        mappedBy = "critere",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Image> images = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public String getOrgane() {
        return organe;
    }

    public void setOrgane(String organe) {
        this.organe = organe;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Taxref getPlante() {
        return plante;
    }

    public void setPlante(Taxref plante) {
        this.plante = plante;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Image> getImages() {
        return images;
    }

    public void addImage(Image image) {
        images.add(image);
        image.setCritere(this);
    }

    public void removeImage(Image image) {
        images.remove(image);
        image.setCritere(null);
    }
}
