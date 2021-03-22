package com.project.cocktailapp.model.service;

import com.project.cocktailapp.model.entity.RoleEntity;
import com.project.cocktailapp.model.entity.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class UserServiceModel {

    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private LocalDateTime registerDate;

    private String imgUrl;

    private Gender gender;

}
