package com.project.cocktailapp.service;

import com.project.cocktailapp.model.entity.enums.BaseAlcoholName;
import com.project.cocktailapp.model.view.AlcoholViewModel;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;

@Service
public interface AlcoholService {
    void importAlcohols() throws FileNotFoundException;

    AlcoholViewModel getAlcoholByName(BaseAlcoholName baseAlcoholName);
}
