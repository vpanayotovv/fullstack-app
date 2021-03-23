package com.project.cocktailapp.model.binding;

import com.google.gson.annotations.Expose;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AlcoholBindingModel {

    @Expose
    private String baseName;

    @Expose
    private String description;

    @Expose
    private double percentOfAlcohol;

}
