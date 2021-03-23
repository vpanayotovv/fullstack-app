package com.project.cocktailapp.repository;

import com.project.cocktailapp.model.entity.CocktailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepository extends JpaRepository<CocktailEntity,Long> {
}
