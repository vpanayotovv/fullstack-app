package com.project.cocktailapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CocktailController {

    @GetMapping("/cocktails")
    public String allCocktails(){
        return "all-cocktails";
    }
}
