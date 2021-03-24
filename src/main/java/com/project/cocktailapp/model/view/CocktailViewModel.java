package com.project.cocktailapp.model.view;

import com.project.cocktailapp.model.entity.enums.BaseAlcoholName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CocktailViewModel {

    private Long id;

    private String name;

    private BaseAlcoholName baseAlcohol;

    private String imgUrl;

}
