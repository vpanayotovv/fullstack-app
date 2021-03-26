package com.project.cocktailapp.service;

import com.project.cocktailapp.model.entity.UserEntity;
import com.project.cocktailapp.model.service.UserServiceModel;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void importAdmin();

    boolean userNameExists(String username);

    void register(UserServiceModel userServiceModel);

    UserEntity getUserByUsername(String username);
}
