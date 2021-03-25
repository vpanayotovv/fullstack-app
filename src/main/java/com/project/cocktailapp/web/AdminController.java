package com.project.cocktailapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/role")
    private String role(){
        return "add-role";
    }

}
