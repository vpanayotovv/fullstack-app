package com.project.cocktailapp.repository;

import com.project.cocktailapp.model.entity.LogDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogDetailsRepository extends JpaRepository<LogDetailEntity, Long> {

    @Query(value = "SELECT cocktail_entity_id FROM log_details GROUP BY cocktail_entity_id ORDER BY COUNT(*) DESC LIMIT 3;",nativeQuery = true)
    List<Integer> findMostViewed();

}
