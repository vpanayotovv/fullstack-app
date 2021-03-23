package com.project.cocktailapp.repository;

import com.project.cocktailapp.model.entity.AlcoholEntity;
import com.project.cocktailapp.model.entity.enums.BaseAlcoholName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AlcoholRepository extends JpaRepository<AlcoholEntity,Long> {
    Optional<AlcoholEntity> findByBaseName(BaseAlcoholName baseAlcohol);
}
