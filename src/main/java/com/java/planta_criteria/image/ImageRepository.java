package com.java.planta_criteria.image;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {

    List<Image> findAllByCritere_IdOrderByIdAsc(
        Integer critereId
    );
}
