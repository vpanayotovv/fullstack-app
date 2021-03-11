package com.project.cocktailapp.model;

import com.project.cocktailapp.model.enums.RoleName;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RoleName role;

    public RoleEntity() {
    }

    public Long getId() {
        return this.id;
    }

    public RoleEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public RoleName getRole() {
        return this.role;
    }

    public RoleEntity setRole(RoleName role) {
        this.role = role;
        return this;
    }
}
