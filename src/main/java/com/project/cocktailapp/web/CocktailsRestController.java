package com.project.cocktailapp.web;

import com.project.cocktailapp.model.view.CocktailViewModel;
import com.project.cocktailapp.service.CocktailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cocktails")
public class CocktailsRestController {

    private final CocktailService cocktailService;

    public CocktailsRestController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping("/api")
    public ResponseEntity<List<CocktailViewModel>> findAll() {
        return ResponseEntity
                .ok()
                .body(cocktailService.getAllCocktails());
    }

}
