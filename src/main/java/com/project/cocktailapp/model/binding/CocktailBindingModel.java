package com.project.cocktailapp.model.binding;

import com.google.gson.annotations.Expose;
import com.project.cocktailapp.model.entity.UserEntity;
import com.project.cocktailapp.model.entity.enums.BaseAlcoholName;
import com.project.cocktailapp.model.entity.enums.MethodName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CocktailBindingModel {

    @Expose
    private String name;

    @Expose
    private BaseAlcoholName baseAlcohol;

    @Expose
    private String imgUrl;

    @Expose
    private String videoUrl;

    @Expose
    private List<ProductBindingModel> products;

    @Expose
    private MethodName method;

    @Expose
    private UserEntity user;

}
