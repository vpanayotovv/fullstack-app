package com.project.cocktailapp.service;

import com.project.cocktailapp.model.view.CocktailViewModel;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.util.List;

@Service
public interface CocktailService {
    void importCocktails() throws FileNotFoundException;

    List<CocktailViewModel> getAllCocktails();
}
