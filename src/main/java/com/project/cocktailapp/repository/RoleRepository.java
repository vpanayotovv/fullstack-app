package com.project.cocktailapp.repository;

import com.project.cocktailapp.model.RoleEntity;
import com.project.cocktailapp.model.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
}
