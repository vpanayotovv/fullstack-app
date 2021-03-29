package com.project.cocktailapp.service;

import com.project.cocktailapp.model.entity.UserEntity;
import com.project.cocktailapp.model.entity.enums.RoleName;
import com.project.cocktailapp.model.service.UserServiceModel;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void importAdmin();

    boolean userNameExists(String username);

    void register(UserServiceModel userServiceModel);

    UserEntity getUserByUsername(String username);

    List<String> getAllUsers();

    void changeRole(String username, RoleName valueOf);
}
