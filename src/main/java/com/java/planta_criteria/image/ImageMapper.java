package com.java.planta_criteria.image;

import org.springframework.stereotype.Component;

import com.java.planta_criteria.image.dto.ImageDto;

@Component
public class ImageMapper {

    public ImageDto toSearchDto(Image image) {
        return new ImageDto(

            image.getId(),
            image.getChemin(),
            image.getAuteur(),
            image.getDescription()
        );
    }
}
