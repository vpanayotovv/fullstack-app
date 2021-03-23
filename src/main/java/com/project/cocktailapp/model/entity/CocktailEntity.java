package com.project.cocktailapp.model.entity;

import com.project.cocktailapp.model.entity.enums.MethodName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "cocktails")
@Getter
@Setter
@NoArgsConstructor
public class CocktailEntity extends BaseEntity {

    @NotNull
    @Column(nullable = false,unique = true)
    private String name;

    @NotNull
    @OneToOne
    private AlcoholEntity baseAlcohol;

    @NotNull
    @Column(nullable = false,columnDefinition = "TEXT")
    private String imgUrl;

    @Column(columnDefinition = "TEXT")
    private String videoUrl;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime addedOn;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<ProductEntity> products;

    @Enumerated(EnumType.STRING)
    private MethodName method;

    @ManyToOne
    private UserEntity user;

}
