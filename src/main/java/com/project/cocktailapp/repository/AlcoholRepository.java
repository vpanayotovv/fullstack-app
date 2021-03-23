package com.project.cocktailapp.repository;

import com.project.cocktailapp.model.entity.AlcoholEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlcoholRepository extends JpaRepository<AlcoholEntity,Long> {
}
