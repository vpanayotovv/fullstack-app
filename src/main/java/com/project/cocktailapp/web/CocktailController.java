package com.project.cocktailapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cocktails")
public class CocktailController {

    @GetMapping("/all")
    public String allCocktails(){
        return "all-cocktails";
    }
}
