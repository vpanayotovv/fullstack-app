package com.project.cocktailapp.repository;

import com.project.cocktailapp.model.entity.RoleEntity;
import com.project.cocktailapp.model.entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleEntity,Long> {
   Optional<RoleEntity> findByRole(RoleName roleName);
}
