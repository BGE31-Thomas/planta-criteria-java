package com.java.planta_criteria.image;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.java.planta_criteria.image.dto.ImageDto;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/images/upload")
public class ImageController {

    private final ImageService imageService;
    private final ImageMapper imageMapper;

    public ImageController(
        ImageService imageService,
        ImageMapper imageMapper
    ) {
        this.imageService = imageService;
        this.imageMapper = imageMapper;
    }

    @PostMapping(
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Image> upload(
        @RequestParam("file") MultipartFile file,
        @RequestParam("observationCritereId")
        Integer observationCritereId
    ) throws IOException {

        Image image =
            imageService.upload(
                file,
                observationCritereId
            );

        return ResponseEntity.ok(image);
    }

    @PostMapping(
    value = "/critere/{critereId}",
    consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ImageDto> uploadPourCritere(
            @PathVariable Integer critereId,
            @RequestParam("file") MultipartFile file
    ) throws IOException {

        Image image = imageService.uploadPourCritere(
            file,
            critereId
        );

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(imageMapper.toSearchDto(image));
    }

    @GetMapping("/critere/{critereId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<ImageDto>> getImagesPourCritere(
            @PathVariable Integer critereId
    ) {
        List<ImageDto> images = imageService
            .getImagesPourCritere(critereId)
            .stream()
            .map(imageMapper::toSearchDto)
            .toList();

        return ResponseEntity.ok(images);
    }

    @DeleteMapping("/{imageId}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> supprimerImage(
            @PathVariable Integer imageId
    ) throws IOException {

        imageService.supprimerImage(imageId);

        return ResponseEntity.noContent().build();
    }
}
