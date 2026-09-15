package com.java.planta_criteria.image;

public class ImageNotFoundException extends RuntimeException {

    public ImageNotFoundException(Long id) {
        super("Image introuvable : " + id);
    }
}
