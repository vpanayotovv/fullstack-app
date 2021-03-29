package com.project.cocktailapp.repository;

import com.project.cocktailapp.model.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Long> {
    Optional<UserEntity> findByUsername(String username);

    @Query("select u.username from UserEntity u order by u.username")
    List<String> finAllByUsername();

}
