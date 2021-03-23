package com.project.cocktailapp.service;

import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public interface AlcoholService {
    void importAlcohols() throws FileNotFoundException;
}
