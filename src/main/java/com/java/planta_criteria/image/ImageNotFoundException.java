package com.java.planta_criteria.image;

public class ImageNotFoundException extends RuntimeException {

    public ImageNotFoundException(Integer id) {
        super("Image introuvable : " + id);
    }
}
