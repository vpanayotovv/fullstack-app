package com.project.cocktailapp.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "log_details")
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
