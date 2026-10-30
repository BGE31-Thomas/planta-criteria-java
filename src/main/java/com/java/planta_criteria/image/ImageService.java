package com.java.planta_criteria.image;


import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.java.planta_criteria.critere.Critere;
import com.java.planta_criteria.critere.CritereRepository;
import com.java.planta_criteria.observation_critere.ObservationCritere;
import com.java.planta_criteria.observation_critere.ObservationCritereRepository;

import jakarta.persistence.EntityNotFoundException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class ImageService {

    private final ImageRepository imageRepository;
    private final ObservationCritereRepository observationCritereRepository;
    private final CritereRepository critereRepository;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public ImageService(
        ObservationCritereRepository observationCritereRepository, 
        CritereRepository critereRepository,
        ImageRepository imageRepository
    ) {
        this.observationCritereRepository = observationCritereRepository;
        this.critereRepository = critereRepository;
        this.imageRepository = imageRepository;
    }

    public Image upload(
        MultipartFile file,
        Integer observationCritereId
    ) throws IOException {

        if (file.isEmpty()) {
            throw new IllegalArgumentException(
                "Le fichier est vide."
            );
        }

        if (
            file.getContentType() == null
            || !file.getContentType().startsWith("image/")
        ) {
            throw new IllegalArgumentException(
                "Le fichier doit être une image."
            );
        }

        ObservationCritere observationCritere =
            observationCritereRepository
                .findById(observationCritereId)
                .orElseThrow(() ->
                    new IllegalArgumentException(
                        "ObservationCritere introuvable : "
                        + observationCritereId
                    )
                );

        Path directory =
            Paths.get(uploadDir);

        Files.createDirectories(directory);

        String filename =
            UUID.randomUUID() + ".webp";

        Path destination =
            directory.resolve(filename);

        Thumbnails
            .of(file.getInputStream())
            .size(250, 250)
            .outputFormat("webp")
            .toFile(destination.toFile());

        Image image = new Image();

        image.setChemin(filename);

        observationCritere.addImage(image);

        observationCritereRepository.save(
            observationCritere
        );

        return image;
    }

    @Transactional
    public Image uploadPourCritere(
            MultipartFile file,
            Integer critereId
    ) throws IOException {

        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException(
                "Le fichier image est vide."
            );
        }

        // Vérifier que le critère existe
        Critere critere = critereRepository.findById(critereId)
            .orElseThrow(() -> new EntityNotFoundException(
                "Critère introuvable : " + critereId
            ));

        // Vérifier le type déclaré du fichier
        String contentType = file.getContentType();

        if (contentType == null ||
                !contentType.startsWith("image/")) {
            throw new IllegalArgumentException(
                "Le fichier fourni n'est pas une image."
            );
        }

        // Générer un nom unique pour le fichier
        String nomFichier = UUID.randomUUID() + ".jpeg";
        
        Path repertoire = Paths.get(uploadDir)
            .toAbsolutePath()
            .normalize();

        Files.createDirectories(repertoire);

        Path destination = repertoire.resolve(nomFichier)
            .normalize();

        if (!destination.startsWith(repertoire)) {
            throw new IllegalArgumentException(
                "Chemin de fichier invalide."
            );
        }

        // Convertir l'image en WebP
        Thumbnails.of(file.getInputStream())
            .size(1600, 1600)
            .outputFormat("jpeg")
            .toFile(destination.toFile());

        // Créer l'entité Image
        Image image = new Image();
        image.setChemin(nomFichier);
        image.setCritere(critere);

        // L'image est associée directement au critère.
        // observationCritere reste null.

        return imageRepository.save(image);
    }

    public List<Image> getImagesPourCritere(Integer critereId) {

        if (!critereRepository.existsById(critereId)) {
            throw new EntityNotFoundException(
                "Critère introuvable : " + critereId
            );
        }

        return imageRepository
            .findAllByCritere_IdOrderByIdAsc(critereId);
    }

    public void supprimerImage(Integer imageId) throws IOException {
        System.out.println("SUpprimer");
        // Récupérer l'image
        Image image = imageRepository.findById(imageId)
            .orElseThrow(() -> new EntityNotFoundException(
                "Image introuvable : " + imageId
            ));

        // Vérifier que l'image est associée à un critère
        if (image.getCritere() == null) {
            throw new IllegalArgumentException(
                "Cette image n'est pas associée à un critère."
            );
        }

        // Construire le chemin du fichier
        Path repertoire = Paths.get(uploadDir)
            .toAbsolutePath()
            .normalize();

        Path fichier = repertoire.resolve(image.getChemin())
            .normalize();

        // Vérifier que le fichier appartient bien au répertoire
        if (!fichier.startsWith(repertoire)) {
            throw new IllegalArgumentException(
                "Chemin de fichier invalide."
            );
        }

        // Supprimer le fichier physique
        Files.deleteIfExists(fichier);

        // Supprimer l'enregistrement en base
        imageRepository.delete(image);
    }
}