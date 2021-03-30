package com.project.cocktailapp.web;

import com.project.cocktailapp.service.CocktailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final CocktailService cocktailService;

    public HomeController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("newestCocktails", cocktailService.getNewestCocktails());
        model.addAttribute("mostViewedCocktails", cocktailService.getMostViewedCocktails());
        return "index";
    }
}
