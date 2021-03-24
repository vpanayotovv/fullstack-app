package com.project.cocktailapp.web;

import com.project.cocktailapp.service.AlcoholService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/alcohols")
public class AlcoholController {

    private final AlcoholService alcoholService;

    public AlcoholController(AlcoholService alcoholService) {
        this.alcoholService = alcoholService;
    }

    @GetMapping("/gin")
    public String gin(Model model){
        model.addAttribute("ginAlcohol",alcoholService.getAlcohol());
        return "gin";
    }
}
