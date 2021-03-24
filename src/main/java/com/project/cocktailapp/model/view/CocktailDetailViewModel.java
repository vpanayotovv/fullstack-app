package com.project.cocktailapp.model.view;

import com.project.cocktailapp.model.entity.UserEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class CocktailDetailViewModel {

    private String name;

    private String baseAlcohol;

    private String imgUrl;

    private String videoUrl;

    private LocalDateTime addedOn;

    private Set<ProductViewModel> products;

    private String method;

    private UserEntity user;

}
