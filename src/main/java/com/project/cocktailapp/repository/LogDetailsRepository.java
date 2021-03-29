package com.project.cocktailapp.repository;

import com.project.cocktailapp.model.entity.LogDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogDetailsRepository extends JpaRepository<LogDetailEntity, Long> {
}
