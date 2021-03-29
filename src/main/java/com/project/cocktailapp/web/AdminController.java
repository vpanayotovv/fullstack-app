package com.project.cocktailapp.web;

import com.project.cocktailapp.model.entity.enums.RoleName;
import com.project.cocktailapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/role-add")
    private String role(Model model){
        if (!model.containsAttribute("users")){
            model.addAttribute("users",userService.getAllUsers());
        }
        return "add-role";
    }

    @PostMapping("/role-add")
    public String addConfirm(@RequestParam String username,
                             @RequestParam String role){


        userService.changeRole(username, RoleName.valueOf(role.toUpperCase()));

        return "redirect:/";
    }

}
