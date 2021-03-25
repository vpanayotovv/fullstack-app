package com.project.cocktailapp.repository;

import com.project.cocktailapp.model.entity.CocktailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CocktailRepository extends JpaRepository<CocktailEntity,Long> {

    @Query("select c from CocktailEntity c order by c.addedOn desc")
    List<CocktailEntity> findNewestCocktails();

    @Query("select c from CocktailEntity c where c.baseAlcohol.id = :id")
    List<CocktailEntity> findAllByBaseAlcoholId(Long id);
}
