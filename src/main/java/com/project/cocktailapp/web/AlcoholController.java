package com.project.cocktailapp.web;

import com.project.cocktailapp.model.entity.enums.BaseAlcoholName;
import com.project.cocktailapp.service.AlcoholService;
import com.project.cocktailapp.service.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alcohols")
public class AlcoholController {

    private final AlcoholService alcoholService;
    private final CocktailService cocktailService;

    @Autowired
    public AlcoholController(AlcoholService alcoholService, CocktailService cocktailService) {
        this.alcoholService = alcoholService;
        this.cocktailService = cocktailService;
    }

    @GetMapping("/gin")
    public String gin(Model model){
        model.addAttribute("ginAlcohol",alcoholService.getAlcoholByName(BaseAlcoholName.Gin));
        model.addAttribute("ginCocktails",cocktailService
                .getCocktailsByAlcoholId(alcoholService.getAlcoholByName(BaseAlcoholName.Gin).getId()));
        return "category/gin-cocktails";
    }

    @GetMapping("/vodka")
    public String vodka(Model model){
        model.addAttribute("vodkaAlcohol",alcoholService.getAlcoholByName(BaseAlcoholName.Vodka));
        model.addAttribute("vodkaCocktails",cocktailService
                .getCocktailsByAlcoholId(alcoholService.getAlcoholByName(BaseAlcoholName.Vodka).getId()));
        return "category/vodka-cocktails";
    }

    @GetMapping("/rum")
    public String rum(Model model){
        model.addAttribute("rumAlcohol",alcoholService.getAlcoholByName(BaseAlcoholName.Rum));
        model.addAttribute("rumCocktails",cocktailService
                .getCocktailsByAlcoholId(alcoholService.getAlcoholByName(BaseAlcoholName.Rum).getId()));
        return "category/rum-cocktails";
    }

    @GetMapping("/other")
    public String other(Model model){
        model.addAttribute("otherAlcohol",alcoholService.getAlcoholByName(BaseAlcoholName.Other));
        model.addAttribute("otherCocktails",cocktailService
                .getCocktailsByAlcoholId(alcoholService.getAlcoholByName(BaseAlcoholName.Other).getId()));
        return "category/other-cocktails";
    }

    @GetMapping("/tequila")
    public String tequila(Model model){
        model.addAttribute("tequilaAlcohol",alcoholService.getAlcoholByName(BaseAlcoholName.Tequila));
        model.addAttribute("tequilaCocktails",cocktailService
                .getCocktailsByAlcoholId(alcoholService.getAlcoholByName(BaseAlcoholName.Tequila).getId()));
        return "category/tequila-cocktails";
    }

    @GetMapping("/whiskey")
    public String whiskey(Model model){
        model.addAttribute("whiskeyAlcohol",alcoholService.getAlcoholByName(BaseAlcoholName.Whiskey));
        model.addAttribute("whiskeyCocktails",cocktailService
                .getCocktailsByAlcoholId(alcoholService.getAlcoholByName(BaseAlcoholName.Whiskey).getId()));
        return "category/whiskey-cocktails";
    }

    @GetMapping("/alcohol-free")
    public String alcoholFree(Model model){
        model.addAttribute("alcoholFreeAlcohol",alcoholService.getAlcoholByName(BaseAlcoholName.AlcoholFree));
        model.addAttribute("alcoholFreeCocktails",cocktailService
                .getCocktailsByAlcoholId(alcoholService.getAlcoholByName(BaseAlcoholName.AlcoholFree).getId()));
        return "category/alcoholfree-cocktails";
    }
}
