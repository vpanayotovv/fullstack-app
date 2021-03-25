package com.project.cocktailapp.web;

import com.project.cocktailapp.service.CocktailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cocktails")
public class CocktailController {

    private final CocktailService cocktailService;

    public CocktailController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping("/all")
    public String allCocktails(Model model){
        model.addAttribute("allCocktails",cocktailService.getAllCocktails());
        return "all-cocktails";
    }

    @GetMapping("/add")
    public String add(){
        return "add-cocktail";
    }

    @GetMapping("/{id}")
    public String profile(@PathVariable Long id, Model model){

        model.addAttribute("cocktail", cocktailService.findCocktailById(id));
        return "details-cocktail";
    }
}
