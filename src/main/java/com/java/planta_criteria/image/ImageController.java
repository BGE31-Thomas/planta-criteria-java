package com.java.planta_criteria.image;

import com.java.planta_criteria.image.dto.ImageDto;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/images")
@PreAuthorize("hasRole('USER')")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping
    public List<ImageDto> findAll() {
        return imageService.findAll();
    }

    @GetMapping("/{id}")
    public ImageDto findById(
        @PathVariable Integer id
    ) {
        return imageService.findById(id);
    }
}
