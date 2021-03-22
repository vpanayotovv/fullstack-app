package com.project.cocktailapp.model.entity;

import com.project.cocktailapp.model.entity.enums.BaseAlcoholName;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "alcohols")
public class AlcoholEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private BaseAlcoholName baseName;

    @NotNull
    @Column(nullable = false)
    private String description;

    @NotNull
    @Column(nullable = false)
    private double procentOfAlcohol;
}
