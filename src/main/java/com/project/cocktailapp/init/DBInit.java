package com.project.cocktailapp.init;

import com.project.cocktailapp.service.RoleService;
import com.project.cocktailapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DBInit implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public DBInit(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @Override
    public void run(String... args){
        roleService.importRoles();
        userService.importAdmin();
    }
}
