package com.project.cocktailapp.service;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public interface CocktailService {
    void importCocktails() throws FileNotFoundException;
}
