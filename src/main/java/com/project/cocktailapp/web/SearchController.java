package com.project.cocktailapp.web;

import com.project.cocktailapp.service.CocktailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SearchController {

    private final CocktailService cocktailService;

    public SearchController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping("/search")
    public String search(Model model){
        model.addAttribute("allCocktails",cocktailService.getAllCocktails());
        return "search";
    }
}
