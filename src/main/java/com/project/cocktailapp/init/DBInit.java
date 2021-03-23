package com.project.cocktailapp.init;

import com.project.cocktailapp.service.AlcoholService;
import com.project.cocktailapp.service.CocktailService;
import com.project.cocktailapp.service.RoleService;
import com.project.cocktailapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;

@Component
public class DBInit implements CommandLineRunner {

    private final RoleService roleService;
    private final UserService userService;
    private final AlcoholService alcoholService;
    private final CocktailService cocktailService;

    @Autowired
    public DBInit(RoleService roleService, UserService userService, AlcoholService alcoholService, CocktailService cocktailService) {
        this.roleService = roleService;
        this.userService = userService;
        this.alcoholService = alcoholService;
        this.cocktailService = cocktailService;
    }

    @Override
    public void run(String... args) throws FileNotFoundException {
        roleService.importRoles();
        userService.importAdmin();
        alcoholService.importAlcohols();
        cocktailService.importCocktails();
    }
}
