package com.project.cocktailapp.web;

import com.project.cocktailapp.service.AlcoholService;
import com.project.cocktailapp.service.CocktailService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alcohols")
public class AlcoholController {

    private final AlcoholService alcoholService;
    private final CocktailService cocktailService;

    public AlcoholController(AlcoholService alcoholService, CocktailService cocktailService) {
        this.alcoholService = alcoholService;
        this.cocktailService = cocktailService;
    }

    @GetMapping("/gin")
    public String gin(Model model){
        model.addAttribute("ginAlcohol",alcoholService.getAlcohol());
        model.addAttribute("ginCocktails",cocktailService
                .getCocktailsByAlcoholId(alcoholService.getAlcohol().getId()));
        return "gin-cocktails";
    }
}
