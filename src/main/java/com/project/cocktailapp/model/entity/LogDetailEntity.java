package com.project.cocktailapp.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "logDetails")
@Getter
@Setter
@NoArgsConstructor
public class LogDetailEntity extends BaseEntity {

    @ManyToOne
    private UserEntity userEntity;

    @ManyToOne
    private CocktailEntity cocktailEntity;

    @Column(nullable = false)
    private String action;

    @Column(nullable = false)
    private LocalDateTime onTime;


}
