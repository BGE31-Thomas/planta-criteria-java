package com.java.planta_criteria.image;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping(
        value = "/upload",
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
}
