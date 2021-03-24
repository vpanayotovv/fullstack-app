package com.project.cocktailapp.model.view;

import com.project.cocktailapp.model.entity.enums.BaseAlcoholName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlcoholViewModel {

    private BaseAlcoholName baseName;

    private String description;

    private double percentOfAlcohol;
}
