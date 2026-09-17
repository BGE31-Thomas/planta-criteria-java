package com.java.planta_criteria.image;

import com.java.planta_criteria.image.dto.ImageDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class ImageService {

    private final ImageRepository imageRepository;
    private final ImageMapper imageMapper;

    public ImageService(
        ImageRepository imageRepository,
        ImageMapper imageMapper
    ) {
        this.imageRepository = imageRepository;
        this.imageMapper = imageMapper;
    }

    public List<ImageDto> findAll() {

        return imageRepository.findAll()
            .stream()
            .map(imageMapper::toSearchDto)
            .toList();
    }

    public ImageDto findById(Integer id) {

        Image image = imageRepository.findById(id)
            .orElseThrow(() ->
                new ImageNotFoundException(id)
            );

        return imageMapper.toSearchDto(image);
    }
}
