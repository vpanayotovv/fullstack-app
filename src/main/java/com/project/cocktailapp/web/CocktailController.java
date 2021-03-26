package com.project.cocktailapp.web;

import com.project.cocktailapp.model.binding.CocktailAddBindingModel;
import com.project.cocktailapp.service.CocktailService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/cocktails")
public class CocktailController {

    private final CocktailService cocktailService;
    private final ModelMapper modelMapper;

    public CocktailController(CocktailService cocktailService, ModelMapper modelMapper) {
        this.cocktailService = cocktailService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/all")
    public String allCocktails(Model model){
        model.addAttribute("allCocktails",cocktailService.getAllCocktails());
        return "all-cocktails";
    }

    @GetMapping("/{id}")
    public String profile(@PathVariable Long id, Model model){

        model.addAttribute("cocktail", cocktailService.findCocktailById(id));
        return "details-cocktail";
    }

    @GetMapping("/add")
    public String add(Model model){
        if (!model.containsAttribute("cocktailAddBindingModel")){
            model.addAttribute("cocktailAddBindingModel",new CocktailAddBindingModel());
            model.addAttribute("cocktailExistsError",false);
        }
        return "add-cocktail";
    }

    @PostMapping("/add")
    public String confirmAdd(@Valid CocktailAddBindingModel cocktailAddBindingModel,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             Principal principal){

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("cocktailAddBindingModel", cocktailAddBindingModel);
            redirectAttributes.addFlashAttribute(
                    "org.springframework.validation.BindingResult.cocktailAddBindingModel", bindingResult);

            return "redirect:add";
        }

        if (cocktailService.cocktailsExist(cocktailAddBindingModel.getName())) {
            redirectAttributes.addFlashAttribute("cocktailAddBindingModel",cocktailAddBindingModel);
            redirectAttributes.addFlashAttribute("cocktailExistsError",true);
            return "redirect:add";
        }

        cocktailAddBindingModel.setUsername(principal.getName());
        cocktailService.addCocktail(cocktailAddBindingModel);


        return "redirect:/cocktails/all";
    }

}
