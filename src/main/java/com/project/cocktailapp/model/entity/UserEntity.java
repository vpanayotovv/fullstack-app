package com.project.cocktailapp.model.entity;

import com.project.cocktailapp.model.entity.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @NotNull
    @Column(nullable = false,unique = true)
    private String username;

    @NotNull
    @Column(nullable = false)
    private String firstName;

    private String lastName;

    @NotNull
    @Column(nullable = false)
    private String password;

    @NotNull
    @Column(nullable = false,unique = true)
    private String email;

    @NotNull
    @Column(nullable = false)
    private LocalDateTime registerDate;

    @Column(columnDefinition = "TEXT")
    private String imgUrl;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<RoleEntity> roles = new ArrayList<>();
}
