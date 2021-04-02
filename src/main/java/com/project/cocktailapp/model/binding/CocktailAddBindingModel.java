package com.project.cocktailapp.model.binding;

import com.project.cocktailapp.config.CustomValidation;
import com.project.cocktailapp.model.entity.enums.BaseAlcoholName;
import com.project.cocktailapp.model.entity.enums.MethodName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
public class CocktailAddBindingModel {

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    private BaseAlcoholName baseAlcohol;

    @NotBlank
    @URL(message = "enter valid url")
    private String imgUrl;

    @NotBlank
    private String videoUrl;

    @NotBlank
    @CustomValidation(regex = "[a-zA-Z ]+-[0-9]+")
    private String products;

    @NotNull
    private MethodName method;

    private String username;

}
