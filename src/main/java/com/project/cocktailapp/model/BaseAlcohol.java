package com.project.cocktailapp.model;

import com.project.cocktailapp.model.enums.BaseAlcoholName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "baseAlcohol")
public class BaseAlcohol extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private BaseAlcoholName baseName;

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private double procentOfAlcohol;
}
