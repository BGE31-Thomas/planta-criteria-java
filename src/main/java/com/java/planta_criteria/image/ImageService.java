package com.java.planta_criteria.image;


import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.java.planta_criteria.observation_critere.ObservationCritere;
import com.java.planta_criteria.observation_critere.ObservationCritereRepository;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@Transactional
public class ImageService {

    private final ObservationCritereRepository observationCritereRepository;

    @Value("${app.upload.dir}")
    private String uploadDir;

    public ImageService(
        ObservationCritereRepository observationCritereRepository
    ) {
        this.observationCritereRepository =
            observationCritereRepository;
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
}